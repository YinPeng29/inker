package cc.mrbird.common.controller;

import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.service.MongoFileService;
import com.alibaba.fastjson.JSONArray;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Date: 2019-01-24 16:08
 * Author: pinoc
 * Desc:
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private GridFsOperations operations;
    @Autowired
    private GridFSBucket gridFSBucket;
    @Autowired
    private MongoFileService mongoFileService;

    /**
     * 显示图片 链接形式为下载
     * @param id
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    void getFile(@RequestParam(name="_id",required = true) String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        GridFSFile gridFSFile = mongoFileService.findOne(MongoFileService.getQuery(id));
        //转化为resource
        GridFsResource gridFsResource=new GridFsResource(gridFSFile,gridFSBucket.openDownloadStream(gridFSFile.getObjectId()));
        String fileName = gridFSFile.getFilename().replace(",", "");
        if(gridFSFile == null ){
            return ;

        }
        if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
                request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
                || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            //非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        IOUtils.copy(gridFsResource.getInputStream(),response.getOutputStream());
    }

    @Log("上传文件")
//    @RequiresPermissions("file:upload")
    @ResponseBody
    @RequestMapping(value = "/uploadFiles",method = RequestMethod.POST)
    public ResponseBo uploadFiles(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        JSONArray jsonArray = new JSONArray();
        for (int i =0; i< files.size(); ++i) {
            file  = files.get(i);
            if(!file.isEmpty()){
                try {
                    String _id = mongoFileService.uploadFile(file);
                    // TODO: 2019/1/24 这里准备获取每个文件的sha2 存储到对应商品-文件关联表。
                    jsonArray.add(_id);
                }catch (Exception e){
                    e.printStackTrace();
                    return ResponseBo.error("上传失败，请联系管理员");
                }
            }
        }
        System.out.println("文件上传成功count: "+jsonArray.size()+" "+jsonArray.toJSONString());
        return ResponseBo.ok("上传成功");
    }

    @Log("删除文件")
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResponseBo deleteFiles(@RequestParam(name="_id",required = true) String id, HttpServletRequest request){
        Query query = MongoFileService.getQuery(id);
        GridFSFile one = mongoFileService.findOne(query);
        if(one == null){
            return ResponseBo.error("文件不存在");
        }
        mongoFileService.deleteFile(query);
        return ResponseBo.ok("删除成功");
    }
}

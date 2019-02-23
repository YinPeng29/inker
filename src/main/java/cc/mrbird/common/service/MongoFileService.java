package cc.mrbird.common.service;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Date: 2019-01-24 17:19
 * Author: pinoc
 * Desc: mongodb 服务类
 */
public interface MongoFileService {

    /**
     * 查找文件
     * @param query
     * @return
     */
    GridFSFile findOne(Query query);
    /**
     * 上传文件
     * @param file
     * @return
     * @throws IOException
     */
    String uploadFile(MultipartFile file) throws IOException;

    /**
     * 删除文件
     * @param query
     * @return
     */
    Boolean deleteFile(Query query);

    public static Query getQuery(String id){
        Query query = new Query();
        return query.addCriteria(Criteria.where("_id").is(id));
    }
}

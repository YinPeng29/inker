package cc.mrbird.common.service.impl;

import cc.mrbird.common.service.MongoFileService;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Date: 2019-01-24 17:19
 * Author: pinoc
 * Desc:
 */
@Service
public class MongoFileServiceImpl implements MongoFileService {

    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private GridFsOperations operations;
    @Autowired
    private GridFSBucket gridFSBucket;

    @Override
    public GridFSFile findOne(Query query) {
        return gridFsTemplate.findOne(query);
    }

    /**
     * 上传文件到mongo
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        ObjectId id = gridFsTemplate.store(inputStream, name);
        return String.valueOf(id);
    }

    @Override
    public Boolean deleteFile(Query query) {
        gridFsTemplate.delete(query);
        return true;
    }

}

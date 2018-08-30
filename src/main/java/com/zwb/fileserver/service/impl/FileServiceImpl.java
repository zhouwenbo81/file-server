package com.zwb.fileserver.service.impl;

import com.zwb.fileserver.model.File;
import com.zwb.fileserver.repository.FileRepository;
import com.zwb.fileserver.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Title: FileServiceImpl
 * </p>
 * <p>
 * Description: 文件服务接口实现类
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/8/30 15:26
 */
@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private FileRepository fileRepository;

    @Override
    public File saveFile(File file) {
        return fileRepository.save(file);
    }

    @Override
    public void removeFile(String id) {
        fileRepository.deleteById(id);
    }

    @Override
    public File getFileById(String id) {
        return fileRepository.findById(id).get();
    }

    @Override
    public List<File> queryFileList() {
        return fileRepository.findAll();
    }
}

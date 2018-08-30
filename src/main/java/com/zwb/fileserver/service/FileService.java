package com.zwb.fileserver.service;

import com.zwb.fileserver.model.File;

import java.util.List;

/**
 * <p>
 * Title: FileService
 * </p>
 * <p>
 * Description: 文件服务接口
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/8/30 15:25
 */
public interface FileService {

    /***
     * @Title: saveFile
     * @Description: 保存文件
     * @param file
     * @return: com.zwb.fileserver.model.File
     * @author zhouwenbo
     * @date 2018/8/30 15:31
     * @version 1.0
     */
    public File saveFile(File file);

    /***
     * @Title: removeFile
     * @Description: 删除文件
     * @param id
     * @return: void
     * @author zhouwenbo
     * @date 2018/8/30 15:32
     * @version 1.0
     */
    public void removeFile(String id);

    /***
     * @Title: getFileById
     * @Description: 根据文件Id获取文件
     * @param id
     * @return: com.zwb.fileserver.model.File
     * @author zhouwenbo
     * @date 2018/8/30 15:32
     * @version 1.0
     */
    public File getFileById(String id);

    /***
     * @Title: queryFileList
     * @Description: 查询文件列表
     * @param 
     * @return: java.util.List<com.zwb.fileserver.model.File>
     * @author zhouwenbo
     * @date 2018/8/30 15:33
     * @version 1.0
     */
    public List<File> queryFileList();


}

package com.zwb.fileserver.repository;

import com.zwb.fileserver.model.File;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <p>
 * Title: FileRepository
 * </p>
 * <p>
 * Description: 文件存储库
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/8/30 15:21
 */

/**
 * 继承MongoRepository后，可以使用增删改查等操作方法
 */
public interface FileRepository extends MongoRepository<File,String> {


}

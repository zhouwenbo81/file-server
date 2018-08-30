package com.zwb.fileserver.controller;

import com.zwb.fileserver.model.File;
import com.zwb.fileserver.service.FileService;
import com.zwb.fileserver.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * Title: FileController
 * </p>
 * <p>
 * Description: 文件控制器 --- 对存放在MongoDB中的文件进行操作
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/8/30 15:38
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    /***
     * @Title: index
     * @Description: 获取文件列表信息
     * @param model
     * @return: java.lang.String
     * @author zhouwenbo
     * @date 2018/8/30 15:47
     * @version 1.0
     */
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("files",fileService.queryFileList());
        return "index";
    }

    /**
     * @Title: serveFile
     * @Description: 获取文件片信息
     * @param: id
     * @return:
     * @author zhouwenbo
     * @date 2018/4/9 15:44
     * @version 1.0
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity serveFile(@PathVariable String id){
        File file = fileService.getFileById(id);
        if(file !=null){
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + file.getName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream" )
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize()+"")
                    .header("Connection",  "close")
                    .body(file.getContent());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
        }
    }

    /**
     * @Title: deleteFile
     * @Description: 根据文件Id删除文件信息
     * @param: id 文件Id
     * @return:
     * @author zhouwenbo
     * @date 2018/4/12 10:37
     * @version 1.0
     */
    @RequestMapping("/delete/{id}")
    public ResponseEntity deleteFile(@PathVariable("id")String id){
        try {
            fileService.removeFile(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("删除失败");
        }
        return ResponseEntity.status(HttpStatus.OK).body("删除成功");
    }

    /**
     * @Title: serveFileOnLine
     * @Description: 在线显示文件
     * @param: id 文件Id
     * @return:
     * @author zhouwenbo
     * @date 2018/4/9 15:57
     * @version 1.0
     */
    @GetMapping("/view/{id}")
    @ResponseBody
    public ResponseEntity serveFileOnLine(@PathVariable String id){
        File file = fileService.getFileById(id);
        if(file !=null){
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + file.getName() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream" )
                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize()+"")
                    .header("Connection",  "close")
                    .body(file.getContent());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
        }
    }

    /**
     * @Title: handleFileUpload
     * @Description: 保存文件
     * @param: file
     * @param: redirectAttributes
     * @return:
     * @author zhouwenbo
     * @date 2018/4/9 15:58
     * @version 1.0
     */
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        try {
            File f = new File(file.getOriginalFilename(),  file.getContentType(), file.getSize(), file.getBytes());
            f.setMd5( MD5Util.getMD5(file.getInputStream()) );
            fileService.saveFile(f);
        } catch (IOException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message",
                    "Your " + file.getOriginalFilename() + " is wrong!");
            return "redirect:/";
        }

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    /**
     * @Title: handleFileUpload
     * @Description:
     * @param: file
     * @return:
     * @author zhouwenbo
     * @date 2018/4/9 16:04
     * @version 1.0
     */
    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        File returnFile = null;
        try {
            File f = new File(file.getOriginalFilename(),  file.getContentType(), file.getSize(),file.getBytes());
            f.setMd5( MD5Util.getMD5(file.getInputStream()) );
            returnFile = fileService.saveFile(f);
            returnFile.setPath("http://localhost:8081/view/"+f.getId());
            returnFile.setContent(null) ;
            return ResponseEntity.status(HttpStatus.OK).body("http://localhost:8081/view/"+f.getId());

        } catch (IOException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }


}

package com.smart.scw.manager.controller.manager;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smart.scw.manager.bean.TAdvertisement;
import com.smart.scw.manager.bean.TUser;
import com.smart.scw.manager.constant.Constants;
import com.smart.scw.manager.exception.CustomException.FileUploadException;
import com.smart.scw.manager.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/serviceCtrl/ads")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @RequestMapping("/list")
    public String list() {
        return "manager/servicemanager/advertisement";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("name") String name,
                         HttpServletRequest request) throws FileUploadException {
        //1 获取某个文件夹在服务器上的真实路径
        ServletContext servletContext = request.getServletContext();
        //2 使用servletContext对象获取真实路径
        String realPath = servletContext.getRealPath("/advertisements");
        //3 把文件上传到这个位置
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String filename = uuid + "_file_" + file.getOriginalFilename();
        try {
            //文件上传后的网络位置
            String netUrl = "advertisements/" + filename;
            file.transferTo(new File(realPath + "/" + filename));
            TAdvertisement tAdvertisement = new TAdvertisement();
            tAdvertisement.setName(name);
            tAdvertisement.setUrl(netUrl);
            TUser user = (TUser) request.getSession().getAttribute(Constants.LOGIN_USER);
            tAdvertisement.setUserid(user.getId());
            //使用service保存到数据库
            boolean flag = advertisementService.addAdver(tAdvertisement);
            Map<String, String> result = new HashMap<>();
            if (flag) {
                result.put("fileUpload", "文件上传成功");
            } else {
                result.put("fileUpload", "文件上传失败");
            }
            return JSON.toJSONString(result);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadException();
        }
    }

    /**
     * 返回所有广告的JSON数据
     */
    @RequestMapping("/json")
    @ResponseBody
    public PageInfo<TAdvertisement> getAdJson(@RequestParam(value = "pn", defaultValue = "1") Integer pageNumber) {
        PageHelper.startPage(pageNumber, 5);
        List<TAdvertisement> ads = advertisementService.getAll();
        PageInfo<TAdvertisement> pageInfo = new PageInfo<>(ads);
        return pageInfo;
    }

}

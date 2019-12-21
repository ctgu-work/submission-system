package com.ctgu.contributionsystem.controller.user;

import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.model.Specialist;
import com.ctgu.contributionsystem.model.SpecialistCategory;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.SpecialService;
import com.ctgu.contributionsystem.service.SpecialistCategoryService;
import com.ctgu.contributionsystem.service.UserService;
import com.ctgu.contributionsystem.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-21 14:59
 * @ClassName ToSpecialist
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/user")
public class ToSpecialist {
    @Autowired
    private UserService userService;

    @Autowired
    private SpecialistCategoryService specialistCategoryService;

    @Autowired
    private SpecialService specialService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * @Author wh
     * @Description 实名认证
     * @Date 2019/12/21 16:36
     * @Param [realName, request, email, phoneNumber]
     * @return java.lang.String
     **/
    @PostMapping("/specialist/checkRealInfo")
    public String checkRealInfo(@RequestParam("realName")String realName, HttpServletRequest request,
                                          @RequestParam("email")String email, @RequestParam("phoneNumber")String phoneNumber){
        try{
            String token = request.getHeader("token");//从请求头中获取token
            Subject subject = SecurityUtils.getSubject();
            if( subject.isAuthenticated() && redisUtils.get("token").equals(token)){
                String tokenPhoneNumber = JwtUtil.getPhoneNumber(token);
                User user = userService.findByPhoneNumber(tokenPhoneNumber);
                if( user.getEmail().equals(email) && user.getPhoneNumber().equals(phoneNumber) && user.getName().equals(realName)){
                    return "1";
                }
                else return "0";
            }
            else{
                return "0";
            }
        }
        catch (Exception e){
            return "0";
        }
    }


    /**
     * @Author wh
     * @Description 提交认证材料
     * @Date 2019/12/21 16:36
     * @Param [proveImages, request, category]
     * @return java.lang.String
     **/
    @PostMapping("/specialist/submitImage")
    public String toSpecialist(@RequestParam("proveImages") List<MultipartFile> proveImages , HttpServletRequest request
                ,@RequestParam("category")Integer category){
        try{
            String token = request.getHeader("token");//从请求头中获取token
            Subject subject = SecurityUtils.getSubject();
            if( subject.isAuthenticated() && redisUtils.get("token").equals(token)){
                boolean flag = true;
                //获取user
                String tokenPhoneNumber = JwtUtil.getPhoneNumber(token);
                User user = userService.findByPhoneNumber(tokenPhoneNumber);
                String photeAddress = ",";//所有图片地址
                //上传图片
                for( MultipartFile multipartFile : proveImages ){
                    String avatarName = multipartFile.getName();
                    String extension = UploadUtil.getFileExtension(multipartFile);
                    //判断是否是图片
                    if( extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".png")
                            || extension.equalsIgnoreCase(".jpeg")){
                        String avatarUrl = Oss.upLoadFile(avatarName,multipartFile);
                        System.out.println(avatarUrl);
                        if( avatarUrl.equals("error") ) {
                            flag = false;
                            break;
                        }
                        photeAddress = photeAddress + avatarUrl + ",";
                    }
                    else{
                        flag = false;
                        break;
                    }
                }
                if( flag ){
                    Specialist specialist = new Specialist();
                    specialist.setCategory(category);
                    specialist.setStatus(1);
                    specialist.setUserId(user.getUserId());
                    specialist.setPhotoAddress(photeAddress);
                    if( specialService.findByUserId(user.getUserId()) == null ){
                        specialService.addSpecialist(specialist);
                        return "1";
                    }
                    else return "0";
                }
                else return "0";
            }
            else{
                return "0";
            }
        }
        catch (Exception e){
            return "0";
        }
    }


    /**
     * @Author wh
     * @Description 获取专家所有分类
     * @Date 2019/12/21 16:36
     * @Param []
     * @return java.util.List<com.ctgu.contributionsystem.model.SpecialistCategory>
     **/
    @GetMapping("/specialist/category")
    public List<SpecialistCategory> allCategory(){
        return specialistCategoryService.findAll();
    }

}

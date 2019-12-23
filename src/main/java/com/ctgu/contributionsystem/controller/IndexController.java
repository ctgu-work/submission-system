package com.ctgu.contributionsystem.controller;

import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.Tag;
import com.ctgu.contributionsystem.service.PaperService;
import com.ctgu.contributionsystem.service.UserService;
import com.ctgu.contributionsystem.utils.RedisUtils;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-20 20:54
 * @ClassName IndexController
 * @Version 1.0.0
 */
@RequestMapping("/index")
@RestController
public class IndexController {
    @Autowired
    private UserService userService;

    @Autowired
    private PaperService paperService;

    /**
     * @Author wh
     * @Description 首页最热话题
     * @Date 2019/12/22 20:53
     * @Param []
     * @return com.ctgu.contributionsystem.dto.ReturnResposeBody
     **/
    @RequestMapping("/hottag")
    public ReturnResposeBody hotTags(){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        try {
            List<String> list = paperService.getHotTagsName();
            List<Integer> list1 = paperService.getHotTagsId();
            List<Tag> list2 = new LinkedList<Tag>();
            for (int i = 0; i < list.size(); i++) {
                Tag tag = new Tag();
                tag.setTagId(list1.get(i));
                tag.setTagDetail(list.get(i));
                list2.add(tag);
            }
            returnResposeBody.setResult(list2);
            returnResposeBody.setStatus("200");
            returnResposeBody.setMsg("success");
            return returnResposeBody;
        }
        catch (Exception e){
            returnResposeBody.setMsg("error");
            return returnResposeBody;
        }
    }

    //主页搜索
    @RequestMapping("/find")
    public List<Paper> FindPaper(HttpRequest request,@Param("name") String name){
        try {
            List<Paper> papers = paperService.findAllByName(name);
            return papers;
        } catch (Exception e){
            return null;
        }
    }


}

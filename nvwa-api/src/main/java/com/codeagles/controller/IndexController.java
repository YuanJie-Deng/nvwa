package com.codeagles.controller;

import com.codeagles.enums.EnumYesOrNo;
import com.codeagles.pojo.Carousel;
import com.codeagles.pojo.Category;
import com.codeagles.service.CarouselService;
import com.codeagles.service.CategoryService;
import com.codeagles.utils.JSONResult;
import com.codeagles.vo.CategoryVO;
import com.codeagles.vo.NewItemsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户控制层
 *
 * @author hcn
 * @create 2020-01-03 11:14
 **/
@Api(value = "首页", tags = {"用于首页展示的相关接口"})
@RestController
@RequestMapping(("/index"))
public class IndexController {

    @Autowired
    private CarouselService carouselService;
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表", httpMethod = "GET")
    @GetMapping("/carousel")
    public JSONResult carousel() {

        List<Carousel> carousels = carouselService.queryAll(EnumYesOrNo.YES.type);
        return JSONResult.ok(carousels);
    }


    /**
     * 首页分类展示需求
     * 1.第一次刷新主页查询大分类，渲染展示到首页
     * 2.如果鼠标上移动到大分类，则加载其子分类的内容，如果已经存在子分类，则不需要加载(懒加载)
     */
    @ApiOperation(value = "获取商品分类(一级分类)", notes = "获取商品分类(一级分类)", httpMethod = "GET")
    @GetMapping("/cats")
    public JSONResult cats() {

        List<Category> categories = categoryService.queryAllRootLevelCat();
        return JSONResult.ok(categories);
    }


    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public JSONResult subCat(
            @ApiParam(name = "rootCatId", value = "一级分类Id", required = true)
            @PathVariable Integer rootCatId) {
        if(rootCatId == null){
            return JSONResult.errorMsg("分类id不存在");
        }
        List<CategoryVO> categoryVOS = categoryService.getSubCatList(rootCatId);
        return JSONResult.ok(categoryVOS);
    }


    @ApiOperation(value = "查询每一级分类下六个商品", notes = "查询每一级分类下六个商品", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCatId}")
    public JSONResult sixNewItem(
            @ApiParam(name = "rootCatId", value = "一级分类Id", required = true)
            @PathVariable Integer rootCatId) {
        if(rootCatId == null){
            return JSONResult.errorMsg("分类id不存在");
        }
        List<NewItemsVO> sixNewItemsLazy = categoryService.getSixNewItemsLazy(rootCatId);
        return JSONResult.ok(sixNewItemsLazy);
    }
}
package com.codegym.casemd4.service.image;


import com.codegym.casemd4.model.Image;
import com.codegym.casemd4.model.Post;
import com.codegym.casemd4.service.IGeneralService;

public interface IServiceImage extends IGeneralService<Image> {
    Image add(Image post);
}

package com.newbit.www.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.newbit.www.dao.*;
import com.newbit.www.vo.*;

@Controller
public class MainController {

	@RequestMapping("/")
	public ModelAndView GameForm(ModelAndView mv, RedirectView rv) {
		rv.setUrl("/www/store/games.nbs");
		mv.setView(rv);
		return mv;
	}
}
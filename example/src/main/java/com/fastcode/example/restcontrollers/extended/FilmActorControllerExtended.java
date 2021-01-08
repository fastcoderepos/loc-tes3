package com.fastcode.example.restcontrollers.extended;

import org.springframework.web.bind.annotation.*;
import com.fastcode.example.restcontrollers.core.FilmActorController;
import com.fastcode.example.application.extended.filmactor.IFilmActorAppServiceExtended;
import com.fastcode.example.application.extended.actor.IActorAppServiceExtended;
import com.fastcode.example.application.extended.film.IFilmAppServiceExtended;
import org.springframework.core.env.Environment;
import com.fastcode.example.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/filmActor/extended")
public class FilmActorControllerExtended extends FilmActorController {

		public FilmActorControllerExtended(IFilmActorAppServiceExtended filmActorAppServiceExtended, IActorAppServiceExtended actorAppServiceExtended, IFilmAppServiceExtended filmAppServiceExtended,
	     LoggingHelper helper, Environment env) {
		super(
		filmActorAppServiceExtended,
    	actorAppServiceExtended,
    	filmAppServiceExtended,
		helper, env);
	}

	//Add your custom code here

}


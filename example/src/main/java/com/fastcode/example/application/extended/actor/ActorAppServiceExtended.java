package com.fastcode.example.application.extended.actor;

import org.springframework.stereotype.Service;
import com.fastcode.example.application.core.actor.ActorAppService;

import com.fastcode.example.domain.extended.actor.IActorRepositoryExtended;
import com.fastcode.example.commons.logging.LoggingHelper;

@Service("actorAppServiceExtended")
public class ActorAppServiceExtended extends ActorAppService implements IActorAppServiceExtended {

	public ActorAppServiceExtended(IActorRepositoryExtended actorRepositoryExtended,
				IActorMapperExtended mapper,LoggingHelper logHelper) {

		super(actorRepositoryExtended,
		mapper,logHelper);

	}

 	//Add your custom code here
 
}


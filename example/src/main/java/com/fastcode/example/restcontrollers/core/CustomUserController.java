package com.fastcode.example.restcontrollers.core;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fastcode.example.domain.core.customuser.CustomUserId;
import com.fastcode.example.commons.search.SearchCriteria;
import com.fastcode.example.commons.search.SearchUtils;
import com.fastcode.example.commons.search.OffsetBasedPageRequest;
import com.fastcode.example.application.core.customuser.ICustomUserAppService;
import com.fastcode.example.application.core.customuser.dto.*;
import com.fastcode.example.application.core.address.IAddressAppService;
import java.util.*;
import java.time.*;
import com.fastcode.example.commons.logging.LoggingHelper;

@RestController
@RequestMapping("/customUser")
@RequiredArgsConstructor
public class CustomUserController {

	@Qualifier("customUserAppService")
	@NonNull protected final ICustomUserAppService _customUserAppService;

    @Qualifier("addressAppService")
	@NonNull  protected final IAddressAppService  _addressAppService;

	@NonNull protected final LoggingHelper logHelper;

	@NonNull protected final Environment env;

	@RequestMapping(method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<CreateCustomUserOutput> create(@RequestBody @Valid CreateCustomUserInput customUser) {
		CreateCustomUserOutput output=_customUserAppService.create(customUser);
		return new ResponseEntity(output, HttpStatus.OK);
	}

	// ------------ Delete customUser ------------
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = {"application/json"})
	public void delete(@PathVariable String id) {

		CustomUserId customuserid =_customUserAppService.parseCustomUserKey(id);
		Optional.ofNullable(customuserid).orElseThrow(() -> new EntityNotFoundException(String.format("Invalid id=%s", id)));

		FindCustomUserByIdOutput output = _customUserAppService.findById(customuserid);
    	Optional.ofNullable(output).orElseThrow(() -> new EntityNotFoundException(String.format("There does not exist a customUser with a id=%s", id)));

		_customUserAppService.delete(customuserid);
    }


	// ------------ Update customUser ------------
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<UpdateCustomUserOutput> update(@PathVariable String id, @RequestBody @Valid UpdateCustomUserInput customUser) {

		CustomUserId customuserid =_customUserAppService.parseCustomUserKey(id);

		Optional.ofNullable(customuserid).orElseThrow(() -> new EntityNotFoundException(String.format("Invalid id=%s", id)));

		FindCustomUserByIdOutput currentCustomUser = _customUserAppService.findById(customuserid);
		Optional.ofNullable(currentCustomUser).orElseThrow(() -> new EntityNotFoundException(String.format("Unable to update. CustomUser with id=%s not found.", id)));


		customUser.setVersiono(currentCustomUser.getVersiono());
		UpdateCustomUserOutput output = _customUserAppService.update(customuserid,customUser);
		return new ResponseEntity(output, HttpStatus.OK);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<FindCustomUserByIdOutput> findById(@PathVariable String id) {

		CustomUserId customuserid =_customUserAppService.parseCustomUserKey(id);
		Optional.ofNullable(customuserid).orElseThrow(() -> new EntityNotFoundException(String.format("Invalid id=%s", id)));

		FindCustomUserByIdOutput output = _customUserAppService.findById(customuserid);
    	Optional.ofNullable(output).orElseThrow(() -> new EntityNotFoundException(String.format("Not found")));

		return new ResponseEntity(output, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity find(@RequestParam(value="search", required=false) String search, @RequestParam(value = "offset", required=false) String offset, @RequestParam(value = "limit", required=false) String limit, Sort sort) throws Exception {

		if (offset == null) { offset = env.getProperty("fastCode.offset.default"); }
		if (limit == null) { limit = env.getProperty("fastCode.limit.default"); }

		if(sort == null || sort.isEmpty()) {
			sort = Sort.by(Sort.Direction.ASC, "firstName");
		}
		
		Pageable Pageable = new OffsetBasedPageRequest(Integer.parseInt(offset), Integer.parseInt(limit), sort);
		SearchCriteria searchCriteria = SearchUtils.generateSearchCriteriaObject(search);

		return ResponseEntity.ok(_customUserAppService.find(searchCriteria,Pageable));
	}
	@RequestMapping(value = "/{id}/address", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<GetAddressOutput> getAddress(@PathVariable String id) {
		CustomUserId customuserid =_customUserAppService.parseCustomUserKey(id);
		Optional.ofNullable(customuserid).orElseThrow(() -> new EntityNotFoundException(String.format("Invalid id=%s",id)));

		GetAddressOutput output= _customUserAppService.getAddress(customuserid);
		Optional.ofNullable(output).orElseThrow(() -> new EntityNotFoundException(String.format("Not found")));

		return new ResponseEntity(output, HttpStatus.OK);
	}

}



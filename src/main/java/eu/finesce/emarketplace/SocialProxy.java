/*
 * (C) Copyright 2014 FINESCE-WP4.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package eu.finesce.emarketplace;

import java.util.GregorianCalendar;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.finesce.emarketplace.domain.SocialEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class SocialProxy.
 */
public class SocialProxy {

	/** The Constant logger. */
	private static final Log logger = LogFactory.getLog(SocialProxy.class);
	
	/** The application path. */
	private static String APPLICATION_PATH = "http://localHost:8080/social2orion/rest";
	
	/** The Constant SOCIAL_DATA_INPUT_PATH. */
	private static final String SOCIAL_DATA_INPUT_PATH = "sendSocialData";
	
	/** The event id. */
	private String EVENT_ID = "social2orion";
	
	/** The today. */
	GregorianCalendar today = new GregorianCalendar();
	
	/** The prop. */
	Properties prop;

	/**
	 * Instantiates a new social proxy.
	 */
	public SocialProxy(){
	
	}

	/**
	 * Send social data.
	 *
	 * @return the response
	 */
	public Response sendSocialData() {
		
		SocialEvent socialEvent = new SocialEvent();
		socialEvent.setSocialID(EVENT_ID);
		socialEvent.setCurrentTime(today.getTimeInMillis());
		socialEvent.setSocialEventDateTime("27-07-2014-20:30:00");
		socialEvent.setSocialEventImportancy(2);
		socialEvent.setSocialEventLocationCoordLat(38.8888);
		socialEvent.setSocialEventLocationCoordLong(38.8888);
		socialEvent.setSocialEventTypology("Basketball match");
		socialEvent.setSocialEventVenueAddress("Via delle Magnolie");
		socialEvent.setSocialEventVenueName("Palazzetto dello Sport");
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(APPLICATION_PATH);
		WebTarget resourceWebTarget = webTarget.path(SOCIAL_DATA_INPUT_PATH);
		Response responseEntity = resourceWebTarget.request(MediaType.APPLICATION_XML).post(Entity.xml(socialEvent));
		return responseEntity;
	}
	

}

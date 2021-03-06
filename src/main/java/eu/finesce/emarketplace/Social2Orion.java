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

import java.io.IOException;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.finesce.emarketplace.context.SocialContextElement;
import eu.finesce.emarketplace.domain.SocialEvent;
import eu.fiware.ngsi.official.ContextElement;
import eu.fiware.ngsi.official.ContextElementList;
import eu.fiware.ngsi.official.UpdateActionType;
import eu.fiware.ngsi.official.UpdateContextRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class Social2Orion.
 *
 * @author LL
 */
@Path("/sendSocialData")
public class Social2Orion {
	
	/** The Constant logger. */
	private static final Log logger = LogFactory.getLog(Social2Orion.class);
	
	/** The register context path. */
	private static  String REGISTER_CONTEXT_PATH = "";
	
	/** The orion server url. */
	private static  String ORION_SERVER_URL = "";
	
	/** The prop. */
	Properties prop;
	
	/** The Constant REG_CTX_PATH. */
	private static final String REG_CTX_PATH = "social2orion.registerContexPath";
	
	/** The Constant ORION_SVR_URL. */
	private static final String ORION_SVR_URL = "social2orion.orionServerUrl";
	
	/** The Constant propertyPath. */
	private static final String propertyPath = "social2orion.properties";

	/** The event id. */
	private String EVENT_ID = "social2orion";

	/**
	 * Send social event ctx ev data to orion.
	 *
	 * @param socialEvent the social event
	 * @return the response
	 */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public final Response sendSocialEventCtxEvDataToOrion(
			SocialEvent socialEvent) {
		setConfigProperties();
		logger.info("Sending social data to Orion");
		socialEvent.setSocialID(EVENT_ID);
		socialEvent.setCurrentTime(System.currentTimeMillis() / 1000);
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(ORION_SERVER_URL);
		WebTarget resourceWebTarget = webTarget.path(REGISTER_CONTEXT_PATH);

		UpdateContextRequest updContextRequest = new UpdateContextRequest();
		updContextRequest.setUpdateAction(UpdateActionType.APPEND);
		ContextElement element = new SocialContextElement(socialEvent);
		ContextElementList elementList = new ContextElementList();
		elementList.getContextElements().add(element);
		updContextRequest.setContextElementList(elementList);
		Entity<UpdateContextRequest> sendXml = Entity.xml(updContextRequest);
		Response responseEntity = resourceWebTarget.request(
				MediaType.APPLICATION_XML).post(sendXml);
		return responseEntity;
	}

	/**
	 * Gets the social sample.
	 *
	 * @return the social sample
	 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public SocialEvent getSocialSample() {
		SocialEvent socialEvent = new SocialEvent();
		socialEvent.setSocialID("22222");
		socialEvent.setSocialEventDateTime("27-07-2014-20:30:00");
		socialEvent.setSocialEventImportancy(2);
		socialEvent.setSocialEventLocationCoordLat(38.8888);
		socialEvent.setSocialEventLocationCoordLong(38.8888);
		socialEvent.setSocialEventTypology("Basketball match");
		socialEvent.setSocialEventVenueAddress("Via delle Magnolie");
		socialEvent.setSocialEventVenueName("Palazzetto dello Sport");
		return socialEvent;
	}

	/**
	 * Sets the config properties.
	 */
	protected void setConfigProperties() {
		prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream(propertyPath));
			REGISTER_CONTEXT_PATH = prop.getProperty(REG_CTX_PATH);
			ORION_SERVER_URL = prop.getProperty(ORION_SVR_URL);   
		} catch (IOException e) {
			logger.error("Error during getProperty ", e);
		}
	}
}

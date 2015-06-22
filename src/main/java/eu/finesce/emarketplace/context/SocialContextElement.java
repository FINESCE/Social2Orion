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
package eu.finesce.emarketplace.context;

import eu.finesce.emarketplace.domain.SocialEvent;
import eu.fiware.ngsi.official.ContextAttribute;
import eu.fiware.ngsi.official.ContextAttributeList;
import eu.fiware.ngsi.official.ContextElement;
import eu.fiware.ngsi.official.EntityId;

// TODO: Auto-generated Javadoc
/**
 * The Class SocialContextElement.
 */
public class SocialContextElement extends ContextElement {

	/** The Constant SOCIAL. */
	private static final String SOCIAL = "Social";
	
	/** The Constant SOC_EV_COORD_LAT. */
	private static final String SOC_EV_COORD_LAT = "socialEventLocationCoordLat";
	
	/** The Constant SOC_EV_COORD_LONG. */
	private static final String SOC_EV_COORD_LONG = "socialEventLocationCoordLong";
	
	/** The Constant SOC_EV_VENUE_ADDRESS. */
	private static final String SOC_EV_VENUE_ADDRESS = "socialEventVenueAddress";
	
	/** The Constant SOC_EV_VENUE_NAME. */
	private static final String SOC_EV_VENUE_NAME = "socialEventVenueName";
	
	/** The Constant SOC_EV_DATE_TIME. */
	private static final String SOC_EV_DATE_TIME = "socialEventDateTime";
	
	/** The Constant SOC_EV_LEVEL_OF_IMPORTANCE. */
	private static final String SOC_EV_LEVEL_OF_IMPORTANCE = "socialEventImportancy";
	
	/** The Constant SOC_EV_EVENT_TIPOLOGY. */
	private static final String SOC_EV_EVENT_TIPOLOGY = "socialEventTypology";
	
	/** The Constant CURRENT_TIME. */
	private static final String CURRENT_TIME = "currentTime";
	

	/**
	 * Instantiates a new social context element.
	 *
	 * @param socialEvent the social event
	 */
	public SocialContextElement(SocialEvent socialEvent) {
		this.contextAttributeList = new ContextAttributeList();

		EntityId id = new EntityId();
		id.setId(socialEvent.getSocialID());
		id.setType(SOCIAL);
		id.setIsPattern(false);
		this.setEntityId(id);
		ContextAttribute attribute = new ContextAttribute();

		// attributes
		attribute = new ContextAttribute();
		attribute.setName(SOC_EV_COORD_LAT);
		attribute.setType("double");
		attribute.setContextValue(socialEvent.getSocialEventLocationCoordLat());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(SOC_EV_COORD_LONG);
		attribute.setType("double");
		attribute.setContextValue(socialEvent.getSocialEventLocationCoordLong());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(SOC_EV_VENUE_ADDRESS);
		attribute.setType("text");
		attribute.setContextValue(socialEvent.getSocialEventVenueAddress());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(SOC_EV_VENUE_NAME);
		attribute.setType("text");
		attribute.setContextValue(socialEvent.getSocialEventVenueName());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(SOC_EV_DATE_TIME);
		attribute.setType("text");
		attribute.setContextValue(socialEvent.getSocialEventDateTime());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(SOC_EV_LEVEL_OF_IMPORTANCE);
		attribute.setType("integer");
		attribute.setContextValue(socialEvent.getSocialEventImportancy());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(SOC_EV_EVENT_TIPOLOGY);
		attribute.setType("text");
		attribute.setContextValue(socialEvent.getSocialEventTypology());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(CURRENT_TIME);
		attribute.setType("sec");
		attribute.setContextValue(socialEvent.getCurrentTime());
		this.getContextAttributeList().getContextAttributes().add(attribute);
	}

}

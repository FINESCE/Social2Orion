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


import java.util.Calendar;
import java.util.Locale;

import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.finesce.emarketplace.Social2Orion;
import eu.finesce.emarketplace.SocialProxy;
import eu.finesce.emarketplace.domain.SocialEvent;

public class SocialClientTest {
	private static Social2Orion social2Orion;

	private static final Log logger = LogFactory.getLog(SocialClientTest.class);
	static Calendar today = Calendar.getInstance(Locale.ITALY);
	
	public static void main(String[] args) throws InterruptedException {
		
	//	createSendSocialData();
		
		logger.info(System.currentTimeMillis() / 1000);
		
		startProxy();
	}
	
	private static void startProxy(){
		SocialProxy proxy = new SocialProxy();
		Response r = proxy.sendSocialData();
		logger.info(r.readEntity(String.class));
	}

	private static void createSendSocialData() throws InterruptedException {
		social2Orion = new Social2Orion();

		SocialEvent socialEvent = new SocialEvent();
		socialEvent.setSocialID("22222");
		socialEvent.setSocialEventDateTime("27-07-2014-20:30:00");
		socialEvent.setSocialEventImportancy(2);
		socialEvent.setSocialEventLocationCoordLat(38.8888);
		socialEvent.setSocialEventLocationCoordLong(38.8888);
		socialEvent.setSocialEventTypology("Basketball match");
		socialEvent.setSocialEventVenueAddress("Via delle Magnolie");
		socialEvent.setSocialEventVenueName("Palazzetto dello Sport");

		Response r = social2Orion.sendSocialEventCtxEvDataToOrion(socialEvent);
		logger.info(r.readEntity(String.class));


	}
}

package com.fbs.web.vaadin.ui.user.contact;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.fbs.dmr.universal.model.contact.ContactMechanism;
import com.fbs.dmr.universal.model.contact.ElectronicAddress;
import com.fbs.dmr.universal.model.contact.EntityContactMechanism;
import com.fbs.dmr.universal.model.contact.PostalAddress;
import com.fbs.dmr.universal.model.contact.TelecommunicationNumber;
import com.fbs.web.dto.ContactMechanismDto;
import com.fbs.web.vaadin.application.MyVaadinApplication;
import com.fbs.web.vaadin.i18n.ApplicationMessages;
import com.fbs.web.vaadin.ui.common.ItemsListScreen;
import com.fbs.web.vaadin.ui.common.items.DetailsComponent;

public interface ContactMechanismHelper
{
	
	static final String CONTACT_MECHANISM_ADDRESS = "contactMechanism.address";
	static final String CONTACT_MECHANISM_TYPE_DESCR = "contactMechanism.contactMechanismType.description";

	static final String ADDRESS1 = "address1";
	static final String ADDRESS2 = "address2";
	static final String CITY = "city";
	static final String POSTAL_CODE = "postalCode";
	static final String COUNTRY = "country";
	static final String ELECTRONIC_ADDRESS = "electronicAddress";
	static final String COUNTRY_CODE = "countryCode";
	static final String AREA_CODE = "areaCode";
	static final String NUMBER = "number";
}

package com.fbs.web.vaadin.i18n;

import java.io.Serializable;

public interface ApplicationMessages extends Serializable
{
	// Application
	public static String ApplicationTitle = "Application.Title";
	
	public static String AdminTabTitleUser = "Admin.TabTitleUser";
	public static String AdminTabTitleTenant = "Admin.TabTitleTenant";

	public static String CommonCancel = "Common.Cancel";
	public static String CommonComment = "Common.Comment";
	public static String CommonDelete = "Common.Delete";
	public static String CommonDetails = "Common.Details";
	public static String CommonDetailsDescription = "Common.DetailsDescription";
	public static String CommonException = "Common.Exception";
	public static String CommonSave = "Common.Save";
	public static String CommonSearch = "Common.Search";
	
	public static String ContactMechanismCreateEAddress = "ContactMechanism.CreateEAddress";
	public static String ContactMechanismCreatePhone = "ContactMechanism.CreatePhone";
	public static String ContactMechanismCreatePostalAddress = "ContactMechanism.CreatePostalAddress";
	
	

	public static String ContactMechanismPurposeTypeId = "ContactMechanismPurposeType.Id";
	public static String ContactMechanismPurposeTypeDescription = "ContactMechanismPurposeType.Description";
	public static String ContactMechanismPurposeTypeTitle = "ContactMechanismPurposeType.Title";
	
	public static String ContactMechanismTypeDescription = "ContactMechanismType.Description";
	public static String ContactMechanismTypeId = "ContactMechanismType.Id";
	public static String ContactMechanismTypeTitle = "ContactMechanismType.Title";
	

	public static String ElectronicAddress = "ElectronicAddress";
	
	public static String ErrorCommon = "Error.Common";
	public static String ErrorDatabase = "Error.Database";
	
	public static String FacilityDescription = "Facility.Description";
	public static String FacilityId = "Facility.Id";
	public static String FacilityTitle = "Facility.Title";
	
	// FacilityContactMechanism
	public static String FacilityContactMechanismFacility = "FacilityContactMechanism.Facility";
	public static String FacilityContactMechanismId = "FacilityContactMechanism.Id";
	public static String FacilityContactMechanismTitle = "FacilityContactMechanism.Title";

	public static String FooterCopyRight = "Footer.CopyRight";

	public static String HeaderWelcome = "Header.Welcome";

	// Language List
	public static String LangEnglish = "Lang.English";
	public static String LangGerman = "Lang.German";
	public static String LangTurkish = "Lang.Turkish";

	// Login Form
	public static String Login = "Login";
	public static String LoginButton = "Login.Button";
	public static String LoginForgotPassword = "Login.ForgotPassword";
	public static String LoginPassword = "Login.Password";
	public static String LoginRegisterNewUser = "Login.RegisterNewUser";
	public static String LoginUser = "Login.User";

	// Logout
	public static String LogoutButton = "Logout.Button";
	
	// Organization
	public static String OrgId = "Organization.Id";
	public static String OrgName = "Organization.Name";
	public static String OrgTitle = "Organization.Title";
	
	//Party
	public static String PartyMasterData = "Party.MasterData";
	public static String PartyName = "Party.Name";
	public static String PartyPostalAddress = "Party.PostalAddress";
	public static String PartyRelationships = "Party.Relationships";
	
	//PartyContactMechanism
	public static String PartyContactMechanismAddress = "PartyContactMechanism.Address";
	public static String PartyContactMechanismComment = "PartyContactMechanism.Comment";
	public static String PartyContactMechanismDetails = "PartyContactMechanism.Details";
	public static String PartyContactMechanismId = "PartyContactMechanism.Id";
	public static String PartyContactMechanismParty = "PartyContactMechanism.Party";
	public static String PartyContactMechanismPurpose = "PartyContactMechanism.Purpose";
	public static String PartyContactMechanismTitle = "PartyContactMechanism.Title";
	
	// PartyRelationship
	public static String PartyRelationshipComment = "PartyRelationship.Comment";
	public static String PartyRelationshipDateFrom = "PartyRelationship.DateFrom";
	public static String PartyRelationshipDateTo = "PartyRelationship.DateTo";
	public static String PartyRelationshipId = "PartyRelationship.Id";
	public static String PartyRelationshipPartyFrom = "PartyRelationship.PartyFrom";
	public static String PartyRelationshipPartyTo = "PartyRelationship.PartyTo";
	public static String PartyRelationshipPriority = "PartyRelationship.Priority";
	public static String PartyRelationshipRoleFrom = "PartyRelationship.RoleFrom";
	public static String PartyRelationshipRoleTo = "PartyRelationship.RoleTo";
	public static String PartyRelationshipStatus = "PartyRelationship.Status";
	public static String PartyRelationshipTitle = "PartyRelationship.Title";
	
	
	// PartyRelationshipType
	public static String PartyRelationshipTypeDescription = "PartyRelationshipType.Description";
	public static String PartyRelationshipTypeId = "PartyRelationshipType.Id";
	public static String PartyRelationshipTypeRoleTypeFrom = "PartyRelationshipType.RoleTypeFrom";
	public static String PartyRelationshipTypeRoleTypeTo = "PartyRelationshipType.RoleTypeTo";
	public static String PartyRelationshipTypeTitle = "PartyRelationshipType.Title";
	public static String PartyRelationshipTypeName = "PartyRelationshipType.Name";
	
	//PartyRole
	public static String PartyRoleId = "PartyRole.Id";
	public static String PartyRoleParty = "PartyRole.Party";
	public static String PartyRoleTitle = "PartyRole.Title";
	
	// PartyRoleType
	public static String PartyRoleTypeDescription = "PartyRoleType.Description";
	public static String PartyRoleTypeId = "PartyRoleType.Id";
	public static String PartyRoleTypeTitle = "PartyRoleType.Title";
	
	// PartyType
	public static String PartyTypeDescription = "PartyType.Description";
	public static String PartyTypeId = "PartyType.Id";
	public static String PartyTypeTitle = "PartyType.Title";
	
	//PartyTypeClassification
	public static String PartyTypeClassificationTitle = "PartyTypeClassification.Title";
	
	//Person
	public static String PersonBirthDate = "Person.BirthDate";
	public static String PersonFirstName = "Person.FirstName";
	public static String PersonGender = "Person.Gender";
	public static String PersonGenderFemaleSalutation = "Person.GenderFemaleSalutation";
	public static String PersonGenderMaleSalutation = "Person.GenderMaleSalutation";
	public static String PersonId = "Person.Id";
	public static String PersonLastName = "Person.LastName";
	public static String PersonMiddleName = "Person.MiddleName";
	public static String PersonTitle = "Person.Title";
	public static String PersonTitleAcademic = "Person.TitleAcademic";
	
	// PostalAddress
	public static String PostalAddressAddress1 = "PostalAddress.Address1";
	public static String PostalAddressAddress2 = "PostalAddress.Address2";
	public static String PostalAddressCity = "PostalAddress.City";
	public static String PostalAddressCountry = "PostalAddress.Country";
	public static String PostalAddressPostalCode = "PostalAddress.PostalCode";

	// Register Form
	public static String RegisterTitle = "Register.Title";
	public static String RegisterNewUser = "Register.NewUser";

	public static String SecurityAccountLocked = "Security.AccountLocked";
	public static String SecurityAuthenticationException = "Security.AuthenticationException";
	public static String SecurityExcessiveAttempts = "Security.ExcessiveAttempts";
	public static String SecurityInvalidCredentials = "Security.InvalidCredentials";
	public static String SecurityUnknownAccount = "Security.UnknownAccount";
	
	public static String TelekommunikationCountryCode = "Telekommunikation.CountryCode";
	public static String TelekommunikationAreaCode = "Telekommunikation.AreaCode";
	public static String TelekommunikationNumber = "Telekommunikation.Number";

	public static String TenantId = "Tenant.Id";
	public static String TenantDescription = "Tenant.Description";
	public static String TenantScreenTitle = "TenantScreen.Title";

	public static String UserAlreadyExists = "User.AlreadyExists";
	public static String UserName = "User.Name";
	public static String UserPassword = "User.Password";

	public static String UserTabTitleContacts = "UserTab.TitleContacts";
	public static String UserTabTitleDocuments = "UserTab.TitleDocuments";
	public static String UserTabTitleParty = "UserTab.TitleParty";
	public static String UserTabTitleRelationships = "UserTab.TitleRelationships";
	public static String UserTabTitleTypes = "UserTab.TitleTypes";
}

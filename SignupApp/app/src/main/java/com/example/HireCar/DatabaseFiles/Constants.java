package com.example.HireCar.DatabaseFiles;

public class Constants {

    // User Table Name
    public static final String USER_TABLE = "User";

    // Table Columns
    public static final String COL_USERID = "Id";
    public static final String COL_NAME = "Name";
    public static final String COL_EMAIL = "Email";
    public static final String COL_PHONE = "MobileNo";
    public static final String COL_DLNUMBER = "DlNo";
    public static final String COL_DLIMAGE = "DLImage";
    public static final String COL_ISADMIN = "isAdmin";


    // Company Table Name
    public static final String COMPANY_TABLE = "Company";

    // Table Columns
    public static final String COL_COMPID = "Id";
    public static final String COL_COMPNAME = "Name";


    // Car Table
    public static final String CAR_TABLE = "Car";

    // Table Columns
    public static final String COL_CARID = "Id";
    public static final String COL_MODELNAME = "ModelName";
    public static final String COL_REGNUMBER = "RegistrationNo";
    public static final String COL_CARIMAGE = "Image";
    public static final String COL_CARINSURANCEIMAGAE = "InsuranceImage";
    public static final String COL_CARPUCIMAGE = "PUCImage";
    public static final String COL_CARRCIMAGE = "RCImage";
    public static final String COL_BODYTYPE = "BodyType";
    public static final String COL_FUELTYPE = "FuelType";
    public static final String COL_COST = "Cost";
    public static final String COL_CAPACITY = "Capacity";
    public static final String COL_MILEAGE = "Mileage";
    public static final String COL_TRANSMISSION = "Transmission";
    public static final String COL_AVAILFLAG = "Available";
    public static final String COL_COMPANY_ID_FK = "CompanyID";



    // Location Table
    public static final String LOCATION_TABLE = "Location";

    // Table Columns
    public static final String COL_LOCATIONANME = "Name";


    // Booking Table
    public static final String BOOKING_TABLE = "Booking";

    // Table Columns
    public static final String COL_BOOKINGID = "Id";
    public static final String COL_STARTDATE = "StartDate";
    public static final String COL_ENDDATE = "EndDate";
    public static final String COL_PICKUPLOC = "PickupLocationName";
    public static final String COL_DROPPLOC = "DropLocationName";
    public static final String COL_PAYMENTSTATUS = "PayStatus";
    public static final String COL_AMOUNT = "Amount";
    public static final String COL_UID_FK = "UserId";
    public static final String COL_CARID_FK = "CarId";
    public static final String COL_REFUNDALBEDEPOSIT = "RefundableDeposit";






}

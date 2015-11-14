package com.meike.abc.meike.Model.Util;

import android.location.Address;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMarshaller;
import com.meike.abc.meike.Model.Support.CompanyInfo;
import com.meike.abc.meike.Model.Support.ContactInfo;
import com.meike.abc.meike.Model.Support.ReviewMessage;

import java.util.Calendar;
import java.util.Locale;

public class Converters {

    //Converts the Calendar
    static public class CalendarConverter implements DynamoDBMarshaller<Calendar> {
        @Override
        public String marshall(Calendar value) {
            Calendar calendar = value;
            String cal = null;
            try {
                if (calendar != null) {
                    cal = String.format("%d / %d / %d / %d / %d / %d",
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DATE),
                            calendar.get(Calendar.HOUR),
                            calendar.get(Calendar.MINUTE),
                            calendar.get(Calendar.SECOND));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return cal;
        }

        @Override
        public Calendar unmarshall(Class<Calendar> calendarType, String value) {
            Calendar itemCalendar = Calendar.getInstance();
            try {
                if (value != null && value.length() != 0) {
                    String[] data = value.split("/");
                    itemCalendar.set(Integer.parseInt(data[0].trim()),
                        Integer.parseInt(data[1].trim()),
                        Integer.parseInt(data[2].trim()),
                        Integer.parseInt(data[3].trim()),
                        Integer.parseInt(data[4].trim()),
                        Integer.parseInt(data[5].trim()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return itemCalendar;
        }
    }

    // Converts the complex type Address to a string and vice-versa.
    static public class AddressConverter implements DynamoDBMarshaller<Address> {

        @Override
        public String marshall(Address value) {
            Address address = value;
            String addr = null;
            try {
                if (address != null) {
                    addr = String.format("%s & %s & %s & %s & %s",
                            address.getAddressLine(0),
                            address.getAddressLine(1),
                            address.getAdminArea(),
                            address.getPostalCode(),
                            address.getLocality());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return addr;
        }

        @Override
        public Address unmarshall(Class<Address> addressType, String value) {

            Address itemAddress = new Address(new Locale("ENGLISH"));
            try {
                if (value != null && value.length() != 0) {
                    String[] data = value.split("&");
                    itemAddress.setAddressLine(0, data[0]);
                    itemAddress.setAddressLine(1, data[1]);
                    itemAddress.setAdminArea(data[2]);
                    itemAddress.setPostalCode(data[3]);
                    itemAddress.setLocality(data[4]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return itemAddress;
        }
    }

    static public class ContactInfoConverter implements DynamoDBMarshaller<ContactInfo> {
        @Override
        public String marshall(ContactInfo contactInfo) {
            ContactInfo contact = contactInfo;
            String cont = null;
            try{
                if(contact != null) {
                    cont = String.format("%s & %s & %s & %s & %s & %s & %s & %s & %s & %s",
                            contact.getName(),
                            contact.getPhoneNumber(),
                            contact.getEmail(),
                            contact.getWeChat(),
                            contact.getQq(),
                            contact.getAddress().getAddressLine(0),
                            contact.getAddress().getAddressLine(1),
                            contact.getAddress().getAdminArea(),
                            contact.getAddress().getPostalCode(),
                            contact.getAddress().getLocality());
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            return cont;
        }

        @Override
        public ContactInfo unmarshall(Class<ContactInfo> aClass, String value) {
            ContactInfo contact = new ContactInfo();
            try{
                if(value != null && value.length() !=0){
                    String[] data = value.split("&");
                    contact.setName(data[0].trim());
                    contact.setPhoneNumber(data[1].trim());
                    contact.setEmail(data[2].trim());
                    contact.setWeChat(data[3].trim());
                    contact.setQq(data[4].trim());

                    Address address = new Address(Locale.ENGLISH);
                    address.setAddressLine(0,data[5]);
                    address.setAddressLine(1,data[6]);
                    address.setAdminArea(data[7]);
                    address.setPostalCode(data[8]);
                    address.setLocality(data[9]);
                    contact.setAddress(address);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            return contact;
        }

    }

    static public class CompanyInfoConverter implements DynamoDBMarshaller<CompanyInfo> {

        @Override
        public String marshall(CompanyInfo companyInfo) {
            CompanyInfo ci = companyInfo;
            String ciStr = null;
            try{
                if(ci != null){
                    ciStr = String.format("%d ~ %s ~ %s ~ %s ~ %s " +
                                    "~ %s ~ %s ~ %s ~ %s ~ %s",
                            ci.getPhoto(),
                            ci.getName(),
                            ci.getIndustry(),
                            ci.getWebsite(),
                            ci.getDescription(),
                            ci.getLocation().getAddressLine(0),
                            ci.getLocation().getAddressLine(1),
                            ci.getLocation().getAdminArea(),
                            ci.getLocation().getPostalCode(),
                            ci.getLocation().getLocality());
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            return ciStr;
        }

        @Override
        public CompanyInfo unmarshall(Class<CompanyInfo> aClass, String value) {
            CompanyInfo ci = new CompanyInfo();
            try{
                if(value != null && value.length() !=0 ){
                    String[] data = value.split("~");
                    ci.setPhoto(Integer.parseInt(data[0].trim()));
                    ci.setName(data[1].trim());
                    ci.setIndustry(data[2].trim());
                    ci.setWebsite(data[3].trim());
                    ci.setDescription(data[4].trim());

                    Address addr = new Address(Locale.ENGLISH);
                    addr.setAddressLine(0,data[5].trim());
                    addr.setAddressLine(1,data[6].trim());
                    addr.setAdminArea(data[7].trim());
                    addr.setPostalCode(data[8].trim());
                    addr.setLocality(data[9].trim());
                    ci.setLocation(addr);
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

    static public class ReviewMessageConverter implements DynamoDBMarshaller<ReviewMessage> {

        @Override
        public String marshall(ReviewMessage rm) {
            ReviewMessage reviewMessage = rm;
            String value = null;
            try {
                if(reviewMessage != null) {
                    value = String.format("%s #$ %d #$ %d #$ %d #$ %d #$ %d #$ %d #$ %s",
                            reviewMessage.getPosterName(),
                            reviewMessage.getPostTime().get(Calendar.YEAR),
                            reviewMessage.getPostTime().get(Calendar.MONTH),
                            reviewMessage.getPostTime().get(Calendar.DATE),
                            reviewMessage.getPostTime().get(Calendar.HOUR),
                            reviewMessage.getPostTime().get(Calendar.MINUTE),
                            reviewMessage.getPostTime().get(Calendar.SECOND),
                            reviewMessage.getMessage());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return value;
        }

        @Override
        public ReviewMessage unmarshall(Class<ReviewMessage> aClass, String value) {
            ReviewMessage rm = new ReviewMessage();
            try{
               if(value != null && value.length() != 0) {
                   String data[] = value.split("#$");
                   String posterName = data[0];
                   Calendar postTime = Calendar.getInstance();
                   postTime.set(Integer.parseInt(data[1]),
                           Integer.parseInt(data[2]),
                           Integer.parseInt(data[3]),
                           Integer.parseInt(data[4]),
                           Integer.parseInt(data[5]),
                           Integer.parseInt(data[6]));
                   String message = data[7];
                   rm.setPosterName(posterName);
                   rm.setPostTime(postTime);
                   rm.setMessage(message);
               }
            }catch (Exception e){
                e.printStackTrace();
            }
            return rm;
        }
    }

}

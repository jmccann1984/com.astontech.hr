//package com.astontech.hr.configuration.SecurityConfigurations;
//
//public class SecurityProfileFactory {
//
//    public SecurityProfileService createSecurityConfigurationService(String profileName){
//
//        SecurityProfileService service = null;
//
//        switch(profileName){
//            case("none"):
//                service = new SecurityProfileNone();
//                break;
//            case("ldap"):
//                service = new SecurityProfileLDAP();
//                break;
//            case("inMemory"):
//                service = new SecurityProfileInMemory();
//                break;
//            case("database"):
//                service = new SecurityProfileDatabase();
//                break;
//            default: service = new SecurityProfileNone();
//        }
//
//        return service;
//    }
//
//
//}

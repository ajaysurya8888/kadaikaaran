package com.kadaikaaran.kadaikaaran.dao;

public class LoginRequest {

        private String identifier;  // Can be email or username
        private String password;

        // Constructors
        public LoginRequest() {}

        public LoginRequest(String identifier, String password) {
            this.identifier = identifier;
            this.password = password;
        }

        // Getters and Setters
        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

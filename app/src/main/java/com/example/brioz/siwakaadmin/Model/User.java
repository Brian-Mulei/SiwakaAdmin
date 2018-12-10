package com.example.brioz.siwakaadmin.Model;


    public class User {
        private String Name;
        private String Password;
        private String textphone;
        private String IsAdmin;



        public User() {
        }

        public User(String name, String password) {
            Name = name;
            Password = password;

        }

        public String getIsAdmin() {
            return IsAdmin;
        }

        public void setIsAdmin(String isAdmin) {
            IsAdmin = isAdmin;
        }

        public User(String name, String password, String textphone) {
            Name = name;
            Password = password;
            this.textphone = textphone;
        }

        public String getTextphone() {
            return textphone;
        }

        public void setTextphone(String textphone) {
            this.textphone = textphone;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String password) {
            Password = password;
        }

    }



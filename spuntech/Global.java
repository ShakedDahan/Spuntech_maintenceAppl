package com.example.spuntech;

    public class Global {
        public static String svar1, svar2;
        public static int count,count1,count2,count3;
        public static String unit;
        public static String subUnit;
        public static String faultType;
        public static String treatType;
        private static String handler ;
        private static String status ;
        private static String notify ;
        private static String priority ;
        public static boolean canSort = false;

        public static boolean isCanSort() {
            return canSort;
        }

        public static void setCanSort(boolean canSort) {
            Global.canSort = canSort;
        }



        public static String getUnit() {
            return unit;
        }

        public static void setUnit(String unit) {
            Global.unit = unit;
        }

        public static String getSubUnit() {
            return subUnit;
        }

        public static void setSubUnit(String subUnit) {
            Global.subUnit = subUnit;
        }

        public static String getFaultType() {
            return faultType;
        }

        public static void setFaultType(String faultType) {
            Global.faultType = faultType;
        }

        public static String getTreatType() {
            return treatType;
        }

        public static void setTreatType(String treatType) {
            Global.treatType = treatType;
        }


        public static String getHandler() {
            return handler;
        }

        public static void setHandler(String handler) {
            Global.handler = handler;
        }

        public static String getStatus() {
            return status;
        }

        public static void setStatus(String status) {
            Global.status = status;
        }

        public static String getNotify() {
            return notify;
        }

        public static void setNotify(String notify) {
            Global.notify = notify;
        }

        public static void setPriority(String priority) {
            Global.priority = priority;
        }

        public static String getPriority() {
            return priority;
        }
    }



package com.example.spuntech;

public class PeriodicFault {
    private String Id;
    private String Unit;
    private String Handler1;
    private String Handler2;
    private String Handler3;
    private String SubUnit;
    private String FaultType;
    private String TreatType;
    private String OpenFaultEmployee;
    private String Date;
    private String Status;
    private String notify;
    private String Priority;
    private String description;

    public PeriodicFault(String id,String unit,String subUnit, String openFaultEmployee, String status, String date,  String notify) {
        Id = id;
        Unit = unit;
        SubUnit = subUnit;
        OpenFaultEmployee = openFaultEmployee;
        Date = date;
        Status = status;
        this.notify = notify;
    }



    public PeriodicFault(String id, String unit, String subUnit, String faultType, String treatType, String openFaultEmployee, String date, String status, String notify) {
        Id = id;
        Unit = unit;
        SubUnit = subUnit;
        FaultType = faultType;
        TreatType = treatType;
        OpenFaultEmployee = openFaultEmployee;
        Date = date;
        Status = status;
        this.notify = notify;
    }

    public PeriodicFault(String id, String unit, String subUnit, String faultType, String treatType, String openFaultEmployee, String date, String status, String notify,String Priority) {
        Id = id;
        Unit = unit;
        SubUnit = subUnit;
        FaultType = faultType;
        TreatType = treatType;
        OpenFaultEmployee = openFaultEmployee;
        Date = date;
        Status = status;
        this.notify = notify;
        this.Priority =Priority;
    }

    public PeriodicFault(String id, String unit, String handler1, String handler2, String handler3, String subUnit, String faultType, String treatType, String openFaultEmployee, String date, String status, String notify, String priority,String description) {
        Id = id;
        Unit = unit;
        Handler1 = handler1;
        Handler2 = handler2;
        Handler3 = handler3;
        SubUnit = subUnit;
        FaultType = faultType;
        TreatType = treatType;
        OpenFaultEmployee = openFaultEmployee;
        Date = date;
        Status = status;
        this.notify = notify;
        Priority = priority;
    }

    public PeriodicFault(String unit, String handler1, String handler2, String handler3, String subUnit, String faultType, String treatType, String openFaultEmployee, String date, String status, String notify, String priority,String description) {
        Unit = unit;
        Handler1 = handler1;
        Handler2 = handler2;
        Handler3 = handler3;
        SubUnit = subUnit;
        FaultType = faultType;
        TreatType = treatType;
        OpenFaultEmployee = openFaultEmployee;
        Date = date;
        Status = status;
        this.notify = notify;
        Priority = priority;
        this.description = description;
    }
    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getSubUnit() {
        return SubUnit;
    }

    public void setSubUnit(String subUnit) {
        SubUnit = subUnit;
    }

    public String getFaultType() {
        return FaultType;
    }

    public void setFaultType(String faultType) {
        FaultType = faultType;
    }

    public String getTreatType() {
        return TreatType;
    }

    public void setTreatType(String treatType) {
        TreatType = treatType;
    }

    public String getOpenFaultEmployee() {
        return OpenFaultEmployee;
    }

    public void setOpenFaultEmployee(String openFaultEmployee) {
        OpenFaultEmployee = openFaultEmployee;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getHandler1() {
        return Handler1;
    }

    public void setHandler1(String handler1) {
        Handler1 = handler1;
    }

    public String getHandler2() {
        return Handler2;
    }

    public void setHandler2(String handler2) {
        Handler2 = handler2;
    }

    public String getHandler3() {
        return Handler3;
    }

    public void setHandler3(String handler3) {
        Handler3 = handler3;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

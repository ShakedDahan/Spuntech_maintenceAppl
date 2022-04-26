package com.example.spuntech;

public class Fault {


    private String Id;
    private String Unit;
    private String SubUnit;
    private String FaultType;
    private String TreatType;
    private String OpenFaultEmployee;
    private String Date;
    private String Status;
    private String Handler1;
    private String Handler2;
    private String Handler3;
    private String Priority;
    private String Description;


    public Fault(String Id,String unit, String subUnit, String faultType, String treatType, String openFaultEmployee,String date, String status, String handler1, String handler2, String handler3, String priority, String description) {
        this.Id = Id;
        Unit = unit;
        SubUnit = subUnit;
        FaultType = faultType;
        TreatType = treatType;
        OpenFaultEmployee = openFaultEmployee;
        Date = date;
        Status = status;
        Handler1 = handler1;
        Handler2 = handler2;
        Handler3 = handler3;
        Priority = priority;
        Description = description;
    }

    public Fault(String unit, String subUnit, String faultType, String treatType, String openFaultEmployee,String date, String status, String handler1, String handler2, String handler3, String priority, String description) {
        Unit = unit;
        SubUnit = subUnit;
        FaultType = faultType;
        TreatType = treatType;
        OpenFaultEmployee = openFaultEmployee;
        Date = date;
        Status = status;
        Handler1 = handler1;
        Handler2 = handler2;
        Handler3 = handler3;
        Priority = priority;
        Description = description;
    }
    public Fault(String id, String unit, String subUnit, String faultType, String treatType, String employee, String date, String status, String description, String handler1, String priority) {
        Id = id;
        Unit = unit;
        SubUnit = subUnit;
        FaultType = faultType;
        TreatType = treatType;
        OpenFaultEmployee = employee;
        Date = date;
        Status = status;
        Description = description;
        Handler1=handler1;
        Priority=priority;
    }



    public Fault(String unit, String subUnit, String employee, String date, String status,String id,String handler1) {
        Id=id;
        Unit = unit;
        SubUnit = subUnit;
        OpenFaultEmployee = employee;
        Date = date;
        Status = status;
        Handler1=handler1;

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

    public String getEmployee() {
        return OpenFaultEmployee;
    }

    public void setEmployee(String employee) {
        OpenFaultEmployee = employee;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
    public String getHandler1() {
        return Handler1;
    }

    public void setHandler1(String handler1) {
        Handler1 = handler1;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
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


}

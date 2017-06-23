package com.lavazza;

/**
 * Created by 7 on 02/03/2017.
 */

public class LiveData {
    public String serial_no;
    public String m_date;
//    public int imageId;
    public String imageId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String status;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getM_date() {
        return m_date;
    }

    public void setM_date(String m_date) {
        this.m_date = m_date;
    }

    public int getM_time() {
        return m_time;
    }

    public void setM_time(int m_time) {
        this.m_time = m_time;
    }

    public int m_time;
//    public String serial_no;

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }


}

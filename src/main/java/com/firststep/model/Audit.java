package com.firststep.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Entity class for Audit
 *
 * @author  Tharindu Hirantha
 * @version 1.0
 * @since   2021/11/27
 */
@MappedSuperclass
public class Audit {

    @Column
    private Date createdDate;
    @Column
    private Date updatedDate;
    @Column
    private String createdBy;
    @Column
    private String updatedBy;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}

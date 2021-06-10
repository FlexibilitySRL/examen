/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.plug.examen.domain.model;

import java.util.Date;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author AGB.
 */
@MappedSuperclass
public class AuditModel {
    
    public Date createdDt;
    public Date updateDt;
    
    public String createdBy;
    public String updateBy;
        
}

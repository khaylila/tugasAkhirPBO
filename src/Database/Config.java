/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author milea
 */
@Entity
@Table(name = "config")
@NamedQueries({
    @NamedQuery(name = "Config.findAll", query = "SELECT c FROM Config c"),
    @NamedQuery(name = "Config.findByKey", query = "SELECT c FROM Config c WHERE c.key = :key"),
    @NamedQuery(name = "Config.findByValue", query = "SELECT c FROM Config c WHERE c.value = :value")})
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "key")
    private String key;
    @Column(name = "value")
    private String value;

    public Config() {
    }

    public Config(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (key != null ? key.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Config)) {
            return false;
        }
        Config other = (Config) object;
        if ((this.key == null && other.key != null) || (this.key != null && !this.key.equals(other.key))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Config[ key=" + key + " ]";
    }
    
}

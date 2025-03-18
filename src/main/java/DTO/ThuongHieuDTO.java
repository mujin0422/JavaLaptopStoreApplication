/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author Dell Vostro
 */
public class ThuongHieuDTO {
    private int maTH;
    private String tenTH;

    public ThuongHieuDTO() {
    }

    public ThuongHieuDTO(int maTH, String tenTH) {
        this.maTH = maTH;
        this.tenTH = tenTH;
    }

    public int getMaTH() {
        return maTH;
    }

    public void setMaTH(int maTH) {
        this.maTH = maTH;
    }

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.maTH;
        hash = 59 * hash + Objects.hashCode(this.tenTH);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ThuongHieuDTO other = (ThuongHieuDTO) obj;
        if (this.maTH != other.maTH) {
            return false;
        }
        return Objects.equals(this.tenTH, other.tenTH);
    }
    
    
}

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
public class CpuDTO {
    private int maCPU;
    private String tenCPU;

    public CpuDTO() {
    }

    public CpuDTO(int maCPU, String tenCPU) {
        this.maCPU = maCPU;
        this.tenCPU = tenCPU;
    }

    public int getMaCPU() {
        return maCPU;
    }

    public String getTenCPU() {
        return tenCPU;
    }

    public void setMaCPU(int maCPU) {
        this.maCPU = maCPU;
    }

    public void setTenCPU(String tenCPU) {
        this.tenCPU = tenCPU;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.maCPU;
        hash = 59 * hash + Objects.hashCode(this.tenCPU);
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
        final CpuDTO other = (CpuDTO) obj;
        if (this.maCPU != other.maCPU) {
            return false;
        }
        return Objects.equals(this.tenCPU, other.tenCPU);
    }
    
    
}

package com.markdb.models;

import android.support.annotation.Nullable;

/**
 * Created by anish on 06-12-2017.
 */

public class BudgetRequest extends Budget {

    private long budgetId;
    private String budgetMonth;
    private Long budgetAmount;
    private Long createdDateInt;
    private Long updatedDateInt;


    @Override
    public long budgetId() {
        return budgetId;
    }

    @Nullable
    @Override
    public String budgetMonth() {
        return budgetMonth;
    }

    @Nullable
    @Override
    public Long budgetAmount() {
        return budgetAmount;
    }

    @Nullable
    @Override
    public Long createdDateInt() {
        return createdDateInt;
    }

    @Nullable
    @Override
    public Long updatedDateInt() {
        return updatedDateInt;
    }

    @Override
    public String toString() {
        return "Budget{"
                + "budgetId=" + budgetId + ", "
                + "budgetMonth=" + budgetMonth + ", "
                + "budgetAmount=" + budgetAmount + ", "
                + "createdDateInt=" + createdDateInt + ", "
                + "updatedDateInt=" + updatedDateInt
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Budget) {
            Budget that = (Budget) o;
            return (this.budgetId == that.budgetId())
                    && ((this.budgetMonth == null) ? (that.budgetMonth() == null) : this.budgetMonth.equals(that.budgetMonth()))
                    && ((this.budgetAmount == null) ? (that.budgetAmount() == null) : this.budgetAmount.equals(that.budgetAmount()))
                    && ((this.createdDateInt == null) ? (that.createdDateInt() == null) : this.createdDateInt.equals(that.createdDateInt()))
                    && ((this.updatedDateInt == null) ? (that.updatedDateInt() == null) : this.updatedDateInt.equals(that.updatedDateInt()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= (int) ((this.budgetId >>> 32) ^ this.budgetId);
        h *= 1000003;
        h ^= (budgetMonth == null) ? 0 : this.budgetMonth.hashCode();
        h *= 1000003;
        h ^= (budgetAmount == null) ? 0 : this.budgetAmount.hashCode();
        h *= 1000003;
        h ^= (createdDateInt == null) ? 0 : this.createdDateInt.hashCode();
        h *= 1000003;
        h ^= (updatedDateInt == null) ? 0 : this.updatedDateInt.hashCode();
        return h;
    }

}

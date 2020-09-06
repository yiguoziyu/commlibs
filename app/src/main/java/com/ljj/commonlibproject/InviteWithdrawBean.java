package com.ljj.commonlibproject;

import com.ljj.lannotation.Persistence;

import java.io.Serializable;

@Persistence
public class InviteWithdrawBean implements Serializable {
    private Long balance;
    private String tip;
    private int is_set_account;
    private WithdrawInfo withdraw_info;
    private Long withdraw_total;
    private Long income_total;
    private String withdraw_tip;

    public String getWithdraw_tip() {
        return withdraw_tip == null ? "" : withdraw_tip;
    }

    public void setWithdraw_tip(String withdraw_tip) {
        this.withdraw_tip = withdraw_tip;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getTip() {
        return tip == null ? "" : tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getIs_set_account() {
        return is_set_account;
    }

    public void setIs_set_account(int is_set_account) {
        this.is_set_account = is_set_account;
    }

    public WithdrawInfo getWithdraw_info() {
        return withdraw_info;
    }

    public void setWithdraw_info(WithdrawInfo withdraw_info) {
        this.withdraw_info = withdraw_info;
    }

    public Long getWithdraw_total() {
        return withdraw_total;
    }

    public void setWithdraw_total(Long withdraw_total) {
        this.withdraw_total = withdraw_total;
    }

    public Long getIncome_total() {
        return income_total;
    }

    public void setIncome_total(Long income_total) {
        this.income_total = income_total;
    }

    public class WithdrawInfo implements Serializable{
        private String account;
        private String real_name;

        public String getAccount() {
            return account == null ? "" : account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getReal_name() {
            return real_name == null ? "" : real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }
    }
}

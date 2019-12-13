#!/usr/bin/python3
# -*- coding: utf-8 -*-

import pandas as pd
from pandas import ExcelWriter
from pandas import ExcelFile

# lib to connect server
import smtplib
from email.MIMEMultipart import MIMEMultipart
from email.MIMEText import MIMEText


class Mail_sender():
    # self:
    #   toaddr
    #   fromaddr
    #   subjects
    #   contacts
    #   auditors
    #   tos

    def __init__(self, froma):
        #self.toaddr = ""
        self.fromaddr = froma

        self.msg = MIMEMultipart()
        self.server = 0


    def read_excel(self):
        # Read in excel file
        fields = ['To', 'Subject', 'Auditor', 'Contact', 'Advisory Fees']
        self.df = pd.read_csv('audit-info.csv', skipinitialspace=True, usecols=fields)
        self.df.dropna(inplace=True)
        print(self.df.keys)

     #   self.rows = self.df.count

        # get the values for a given column
      #  self.tos = self.df['To']
       # print("To " + self.to)
       # self.subjects = self.df['Subject']
       # self.auditors = self.df['Auditor']
       # self.contacts = self.df['Contact']

        # TODO advisory fees



    def print_fields(self, f, t, s, a, c):
        print("From %s, To %s, Subject %s, Auditor %s, Contact %s" % (f, t, s, a, c))

    def write_email(self, toaddr, subject, auditor, contact):
        gmail_user = self.fromaddr
        FROM = gmail_user
        TO = toaddr
        SUBJECT = subject
        body = ""

        self.print_fields(FROM, TO, SUBJECT, auditor, contact)

        body = """Auditor %s and GRG (#WMF US - Audit Requests %s)\n\n""" % (auditor, contact)

        body += "Please find the attached response to your " \
            "request. I have attached the participant account statement " \
            "and most recent audited financial statements for the applicable" \
            " portfolios. In addition, please find a copy of the Plan and " \
            "Declaration of Trust. A hard copy of this audit response is " \
            "available upon request.\n\n"
        body += "Advisory fees accrued internal to the portfolio’s NAV " \
            "are detailed in the financial statements.  Advisory " \
            "fees and any applicable performance fees accrued external " \
            "to the portfolio’s NAV are billed directly to participants " \
            "and are included below, if applicable."

        # TODO advisory fees table

        TEXT = body
        #self.msg.attach(MIMEText(body, 'plain'))


def connect_to_server(mail):
    try:
        mail.server = smtplib.SMTP('smtp.gmail.com', 587)
        mail.server.set_debuglevel(1)
        mail.server.ehlo()
        mail.server.starttls()
        mail.server.login(mail.fromaddr, "010203..")
    except :
        print("Error: unable to connect to server")

def send_email(mail):
    try:
        mail.server.sendmail(mail.FROM, mail.TO, mail.TEXT)
        print("Mail sent successfully")
        mail.server.quit()
    except:
        print("Error: unable to send mail")


if __name__ == '__main__':
    mail = Mail_sender("yiwenxin0123@gmail.com")

    mail.read_excel()
    mail.__dict__

    connect_to_server(mail)

    for index, row in mail.df.iterrows():
        mail.write_email(row['To'], row['Subject'], row['Auditor'], row['Contact'])  # TODO advisor feess
        send_email(mail.server)



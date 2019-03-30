CREATE TABLE Guarantees_Receipts 
(Guarantee_ID int(10) NOT NULL AUTO_INCREMENT,
 Receipt_ID int(10) NOT NULL,
 Guarantee_date_of_end time NOT NULL,
 PRIMARY KEY (Guarantee_ID),
 UNIQUE INDEX (Guarantee_ID));

CREATE TABLE Delegation_Mode 
(Delegation_Mode_ID int(10) NOT NULL AUTO_INCREMENT, 
Delegation_Mode_name varchar(255) NOT NULL UNIQUE,
 PRIMARY KEY (Delegation_Mode_ID),
 UNIQUE INDEX (Delegation_Mode_ID));

CREATE TABLE Users_Billings 
(Users_Billing_ID int(10) NOT NULL AUTO_INCREMENT,
 Billing_Group_Member_Lender_ID int(10) NOT NULL, 
Billing_Group_Member_Borrower_ID int(10) NOT NULL,
 Users_Billing_money_amount real NOT NULL,
 Users_Billing_name_of_reason varchar(255),
 PRIMARY KEY (Users_Billing_ID), 
UNIQUE INDEX (Users_Billing_ID));

CREATE TABLE Shopping_List_Elements
 (Shopping_List_Element_ID int(10) NOT NULL AUTO_INCREMENT,
 Shopping_List_Element_name int(10) NOT NULL,
 Shopping_List_Element_checked tinyint NOT NULL,
 PRIMARY KEY (Shopping_List_Element_ID), 
UNIQUE INDEX (Shopping_List_Element_ID));

CREATE TABLE Receipts_Products
 (Receipt_Product_ID int(10) NOT NULL AUTO_INCREMENT, 
Receipt_ID int(10) NOT NULL,
 Product_ID int(10) NOT NULL,
 Receipt_Product_tag int(1), 
Receipt_Product_image_path varchar(255),
 PRIMARY KEY (Receipt_Product_ID),
 UNIQUE INDEX (Receipt_Product_ID));

CREATE TABLE Chars_Categories
 (Chart_Category_ID int(10) NOT NULL AUTO_INCREMENT,
 Chart_ID int(10) NOT NULL,
 Category_ID int(10) NOT NULL,
 PRIMARY KEY (Chart_Category_ID), 
UNIQUE INDEX (Chart_Category_ID),
 INDEX (Chart_ID), 
INDEX (Category_ID));

CREATE TABLE Chart_Types 
(Char_type_ID int(10) NOT NULL AUTO_INCREMENT, 
Char_type_name varchar(255) NOT NULL, 
PRIMARY KEY (Char_type_ID),
 UNIQUE INDEX (Char_type_ID));

CREATE TABLE Receipts_Receipt_images
 (Receipts_Receipt_image_ID int(10) NOT NULL AUTO_INCREMENT,
 Receipt_ID int(10) NOT NULL,
 Receipt_Image_ID int(10) NOT NULL,
 PRIMARY KEY (Receipts_Receipt_image_ID),
 UNIQUE INDEX (Receipts_Receipt_image_ID));

CREATE TABLE Receipts_Tags 
(Receipt_Tag_ID int(10) NOT NULL AUTO_INCREMENT,
 Receipt_ID int(10) NOT NULL,
 Tag__ID int(10) NOT NULL, 
PRIMARY KEY (Receipt_Tag_ID),
 UNIQUE INDEX (Receipt_Tag_ID),
 INDEX (Receipt_ID),
 INDEX (Tag__ID));

CREATE TABLE Receipt_images 
(Receipt_image_ID int(10) NOT NULL AUTO_INCREMENT,
 Receipt_image_path varchar(255),
 PRIMARY KEY (Receipt_image_ID), 
UNIQUE INDEX (Receipt_image_ID));

CREATE TABLE Tags 
(Tag_ID int(10) NOT NULL AUTO_INCREMENT, 
Tag_name varchar(255) NOT NULL UNIQUE,
 PRIMARY KEY (Tag_ID),
 UNIQUE INDEX (Tag_ID));

CREATE TABLE Shops 
(Shop_ID int(10) NOT NULL AUTO_INCREMENT,
 User_ID int(10) NOT NULL,
 Shop_name varchar(255) UNIQUE,
 PRIMARY KEY (Shop_ID),
 UNIQUE INDEX (Shop_ID));

CREATE TABLE Guarantees_Products 
(Guarantee_ID int(10) NOT NULL AUTO_INCREMENT, 
Receipt_Product_ID int(10) NOT NULL,
 Guarantee_date_of_end time NOT NULL, 
PRIMARY KEY (Guarantee_ID),
 UNIQUE INDEX (Guarantee_ID));

CREATE TABLE Charts
 (Chart_ID int(10) NOT NULL AUTO_INCREMENT, 
Char_type_ID int(10) NOT NULL,
 User_ID int(10) NOT NULL, 
Char_image_path varchar(255) NOT NULL UNIQUE, 
PRIMARY KEY (Chart_ID),
 UNIQUE INDEX (Chart_ID));

CREATE TABLE Receipts 
(Receipt_ID int(10) NOT NULL AUTO_INCREMENT,
 Shop_ID int(10) NOT NULL,
 Category_ID int(10) NOT NULL,
 User_ID int(10) NOT NULL,
 Receipt_date_of_purchase time,
 Receipt_price real, 
Receipt_notes varbinary(255), 
PRIMARY KEY (Receipt_ID), 
UNIQUE INDEX (Receipt_ID));

CREATE TABLE Icons 
(Icon_ID int(10) NOT NULL AUTO_INCREMENT, 
User_ID int(10) NOT NULL, 
Icon_colour int(10) NOT NULL, 
icon_vector_image_path varchar(255) NOT NULL,
 PRIMARY KEY (Icon_ID), 
UNIQUE INDEX (Icon_ID));

CREATE TABLE Categories
 (Category_ID int(10) NOT NULL AUTO_INCREMENT, 
Icon_ID int(10) NOT NULL,
 Category_name varchar(255) NOT NULL UNIQUE, 
Category_budget real, 
Category_budget_threshold real, 
UsersUser_ID int(10) NOT NULL, 
CONSTRAINT Kategoria_ID PRIMARY KEY (Category_ID), 
UNIQUE INDEX (Category_ID));

CREATE TABLE Products
 (Product_ID int(10) NOT NULL AUTO_INCREMENT,
 User_ID int(10) NOT NULL, 
Product_name varchar(255) UNIQUE,
 Product_image_path varchar(255), 
Product_favourite tinyint NOT NULL,
 PRIMARY KEY (Product_ID),
 UNIQUE INDEX (Product_ID));

CREATE TABLE Delegations
 (Delegation_ID int(10) NOT NULL AUTO_INCREMENT,
 User_ID int(10) NOT NULL,
 Delegation_Mode_ID int(10) NOT NULL, 
Delegation_name varchar(255) NOT NULL, 
Delegation_description varbinary(255) NOT NULL, 
Delegation_active tinyint NOT NULL,
 PRIMARY KEY (Delegation_ID), 
UNIQUE INDEX (Delegation_ID));

CREATE TABLE Billing_Group_Members 
(Billing_Group_Member_ID int(10) NOT NULL AUTO_INCREMENT, 
Member_ID int(10) NOT NULL,
 Member_Adder_ID int(10) NOT NULL, 
Billing_Group_ID int(10) NOT NULL,
 PRIMARY KEY (Billing_Group_Member_ID), 
UNIQUE INDEX (Billing_Group_Member_ID));

CREATE TABLE Billing_Groups 
(Billing_Group_ID int(10) NOT NULL AUTO_INCREMENT, 
Creator_ID int(10) NOT NULL,
 Billing_Group_name varchar(255), 
PRIMARY KEY (Billing_Group_ID),
 UNIQUE INDEX (Billing_Group_ID));

CREATE TABLE Modes
 (Mode_ID int(10) NOT NULL AUTO_INCREMENT, 
Mode_name varchar(255) NOT NULL UNIQUE, 
PRIMARY KEY (Mode_ID), 
UNIQUE INDEX (Mode_ID));

CREATE TABLE Users 
(User_ID int(10) NOT NULL AUTO_INCREMENT, 
Mode_ID int(10) NOT NULL,
 Is_Online tinyint NOT NULL,
 PRIMARY KEY (User_ID), 
UNIQUE INDEX (User_ID));

CREATE TABLE Users_Shopping_Lists
 (Users_Shopping_List_ID int(10) NOT NULL AUTO_INCREMENT, 
User_ID int(10) NOT NULL,
 Shopping_List_Element_ID int(10) NOT NULL, 
Shopping_List_ID int(10) NOT NULL,
 Active tinyint NOT NULL,
 PRIMARY KEY (Users_Shopping_List_ID),
 UNIQUE INDEX (Users_Shopping_List_ID));

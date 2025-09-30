PROJECT TITLE: DVD Rental Store Management System
PURPOSE OF PROJECT: To manage the database of a rental store
VERSION or DATE:
HOW TO START THIS PROJECT: Just use main thats all thats needed
AUTHORS: Conor Arundel-Conlon
USER INSTRUCTIONS: Run Main and follow the on screen instructions

All DB files are in the application folder, they are simple txt files
This is the format for DB Files:
-----------------------------------------------
# Customers                                   |
A124,bob,bob@gmail.com,153151562,Silver
A147,tim,tim@gmail.com,074462751,Gold

# DVDs
Movie,Cars,10,1,100,148,Jesus Christer

# RentedDVDs
Movie,Inception,10,1,100,148,Christopher Nolan
Series,Breaking Bad,20,2,200,62,47

# Rentals
A124,Inception,2024-12-01
A147,Breaking Bad,2024-12-02                  |
-----------------------------------------------
First declare where its going by putting the data under the correct header,
Then add the data in this order for the respective data types:
(Customers)(ID,Name,Email,PhoneNumber,MembershipStatus)
(DVDMovie)("Movie",title,price(euro),rentalperiod(day),PricePerDay(euro),Length(minutes),director)
(DVDSeries)("Series",title,price(euro),rentalperiod(day),PricePerDay(euro),episodeNum,epLength(Minutes)
Rented dvd's have identical datatypes as dvd
(Rentals)("Customer(ID),DVD(title),rentalDate(yyyy-mm-dd))

Make sure to space data entries with a ',' not adding it to the end.
No white space, and case is important for the datatype headers 

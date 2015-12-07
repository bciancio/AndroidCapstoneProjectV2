# AndroidCapstoneProjectV2

Project purpose:
The purpose of this project is create a mobile specific user interface to interact with my java restful web service.

###### 11/17/2015 Progress Notes

- I've gotten both the fragments wired up.
- Currently working on populating the RecyclerView ListFrag
- Getting failure when I make a request to my service? Ask lori?

11/17/2015 - 11/27/2015
- Been working on the web service side of the application.
- The webservice at this time is just missing a delete transaction from database.
- Working on hardcoding a transactions object with some data to get a working recycler view.
- Will be looking more into how to call the webservice.
- The webservice is hosted on Heroku. The webservice uses clearDbMySql for the database.

11/27/2015 - 12/06/2015

Got a recycler view working! I think I figured out how I need to make my xml webService data be transformed on the viewHolder.

Below is the things i need to in both android / java in order to get this working as I want.
Android
	- Make working calls to webservice.
	- Add a 'add' button to the ListFragments layout.
		- the onClick listener will redirect the user to the AddTransactionFragment
	- In the AddTransactionFragment:
		- Reformat how it looks
			- get rid of the floating action bar?
		- When the user submits a transaction to be added snackbar its success.
	- Make a Transaction POJO
	- When I call the webservice map a Transactions object, than loop through the arrays in this object to create an ArrayList<Transaction> that I will use to pass to MyAdapter for the listview.
	If I mimic these two links:
		1) http://blog.strikeiron.com/bid/73189/Integrate-a-REST-API-into-Android-Application-in-less-than-15-minutes
		2) http://stackoverflow.com/questions/14890252/how-to-parse-complex-xml-using-xmlpullparser

Java
- Create new drivers, create new methods in sold/purchased daos - the new drivers use these new methods.

	- Making a driver for both sold/purchased transactions.

		-SoldTransactionDriver
			- delete @Path /soldTransaction/delete/{id}
			- modify @Path /soldTransaction/modify/{id}
		-PurchasedTransactionDriver
			- delete @Path /purcahsedTransaction/delete/{id}
			- modify @Path /purcahsedTransaction/modify/{id}

	- Modifying sold/purchased DAO

		-SoldTransactionDAO
			- delete (by id)
			- modify (by id)
		-PurchasedTransactionDAO
			- delete (by id)
			- modify (by id)

- Get the finished thing (with these changes) on Heroku

######ReminderForNext######
	* Android Query Structure.
		* Convert JSON String to query String in android?:
		* http://stackoverflow.com/questions/30981016/is-\there-\any-\way-\to-\convert-\json-\string-\to-\query-\string-\in-\android
	* Post JSONArray to REST service.
		* http://stackoverflow.com/questions/21269968/post-\jsonarray-\to-\rest-\service

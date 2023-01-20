# Everest
Team member contribution: 
Pham Hoang Thien An:

Functionality: Our app is a mobile online bookstore for android devices. First timer user can sign up an account to access our store. An account will require the user to insert their name, email, password, phone number, and address.
After signing up for an account, they will be able to sign in using that account by clicking the sign in buttom. 
In the sign in view, the user only need to submit the email and password of their newly created account. Our system will include a login authorization to check if the email and password that the user provided is included in our database. 
After the user login, they will be met with our homepage, in the homepage the application will provide 2 list of books that is currently for sale for the shop.
The first horizontal list will display the books that currently hold the highest rating - 5. The second list, which is a vertical one will display all of the book that are currently for sell. 
In the second list, the user can access another view that will display the description and all of the other details of the book by clicking into the image of the book that they wanted to view. 
If the user want to buy a book, they can add it into the shopping cart by clicking the shopping cart icon next to the book that they wanted to buy, or click into the same shopping cart icon that is included in the book detail view.
After the user has chosen all of the book that they want, user can click to the cart button to view their cart list. In the show cart view, user will have a checkbox in each of item in the cart and the text view total amount will calculate the total automatically. There is also a select all check box to choose all items in the cart and will change the buuton to deselect all which can deselect every item. If user selects every item by clicking check box from each item, it also auto check the select all check box and update the text to deselect all. There is a check out button for user to buy the item user has chose, which must be at least 1 ittem or there will pop up an error message. After checking out, the app will move to the delivery information form which show the user's detail from the database. User can edit these texts for new delivery address. This appliaction also have a text that show the total amount user need to pay. When user clicks confirm, it will show the success screen with a button to return to home page.


Technology used: We implemented Firebase firestore into our project as a our persistent database.
Furthermore, we also used Picasso API to display images in imageView using the URL of the image. Link to the API is here: https://github.com/square/picasso


To get user information, we used FirebaseFirestore, FirebaseAuthentication, and Intent to send the user information when registered to display the user information and store user information into the Firestore. The Firestore will then save the user information under a JSON-like file. To set the button we used Intent to change around the activities to have a better usage of the application.

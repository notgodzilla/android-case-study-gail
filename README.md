
### Libraries
- Moshi - For parsing JSON objects into Kotlin
- Retrofit - Networking
- Glide - Image loading
- Hilt - Dependency injection
- Jetpack Navigation, safeArgs - For fragment navigation and null-safety (safeArgs)
- viewBinding - Type safety when finding views
- JUnit, Mockk for testing
- Various AndroidX / KotlinX libraries (RecyclerView, CardView, coroutines threads, networking)


### Networking
API Endpoints 
- Base URL endpoint: https://api.target.com/mobile_case_study_deals/v1/
- Grabbing deals list: /deals/
- Grabbing deal item product detail: /deals/(productId) where (productId) is the corresponding product ID for that product


### Steps to run the app & functionality 
- Build apk then run on device. App will grab list from /deals/ endpoint and display products in a list.
- If `SalePrice` is not null for a product, then the `SalePrice` will be displayed as the display price and the regular price will be shown beside the sale price.
- Otherwise, if item is not sale then only `RegularPrice` will be displayed.
- If internet connection is lost, then a generic "An error occurred during search" message will display along with a "Refresh" button to allow users to refresh the app and call the /deals/ endpoint again. I implemented this to match the current functionality of the Target retail app when connection is lost.
- If a deal item is selected an an `ItemNotFoundResponse` is returned, then the product photo will show an empty cart and display the `message` attached to the `ItemNotFoundResponse` is displayed underneath. The add to cart button and product description is also hidden when this occurs.
- 'Add to cart' button contains no real functionality and only shows a Toast message indicating the item was added to cart 
- Utilizes Hilt for dependency injection so that a new instance of the `DealsRepository` isn't made when navigating between the deals list fragment and deal item product detail fragment.
- Provides a single instance of Retrofit with dependency injection.

### Known Bugs 
- When navigating back from a deal item product page back to the deals list then selecting a different deal, the product page will briefly show the details of the last product selected before populating the view with the new product details
- The product page only accounts for `ItemNotFondResponse` errors and when a network error occurs then the page will still display all the components such as product description, add to cart button, etc but not populated with data
- If network connection is lost when navigating away from a deal item product page (navigating back to deal list) then navigating to a new product page, the old product will persist in the display
- 
### Future Features 
- Adding functionality to 'Add to cart' by persisting items added to cart - I initially wanted to keep track of items added to cart by product ID and store them temporarily in shared preferences or a Room database to persist a user's cart.
- For more cart functionality, I wanted to add a drop down menu from the app bar to show current items in cart including cart total and possibly dollar amount saved if any items in cart were on sale.
- Ability to clear cart and remove items.
- I initially wanted to refactor a lot of repeated values such for view styling (padding, margin, color, style, etc) by making custom styles and values. 
- Currently only the deal list has a loading spinner when fetching deals - a loading spinner should also be added to the deal item product page.
 

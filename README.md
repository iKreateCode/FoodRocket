🚀FoodRocket Android Application
---
Android Application connected with a RESTful API which offers flexible eCommerce platform features.

## Demo
Demo of this application can be seen in this projects implementation: https://dev.to/bywaleed/foodrocket-5do8

## Features

- OAuth Authentication through RESTful API
- Dynamically fetch and display items & offers from API
- Add items to cart
- Cart checkout to place order
- Automatic WhatsApp notification through [API](https://github.com/iKreateCode/FoodRocket-Backend)

## Set up

### Requirements

- Android Studio 3.6+
- Android API 27+

### API Setup

This application works well with the already created [API](https://github.com/iKreateCode/FoodRocket-Backend), 
but other API maybe used after API fetch methods and url endpoints are updated accordingly.

### Local development

After the above requirements have been met:

1. Clone this repository and `cd` into it

```bash
git clone https://github.com/iKreateCode/FoodRocket.git
cd FoodRocket
```

2. Open Android Studio (terminal or GUI can be used)
```bash
./studio.sh
```

3. File > New > Import Project and select the downloaded/cloned folder.

4. Wait for gradle to sync and build app (click on green hammer or use Shift + F10).

That's it!

## License

[MIT](http://www.opensource.org/licenses/mit-license.html)

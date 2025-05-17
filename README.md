# 🐾 JPetStore Demo Automation Project
- Automation framework to test key user flows of the JPetStore Demo web application using Selenium WebDriver with Java and TestNG.

## 📌 Automation Scenarios
### Scenario Description
✅  **User Registration** -	Automate user registration with dynamic data using Java Faker.  
✅ 	**Sign In** -	Automate sign-in process using registered credentials.  
✅ 	**Add to Cart** -	Automate adding any item from catalog to the shopping cart  

## 🧰Tools
| Tool             | Purpose                           |
|------------------|-----------------------------------|
| Java             | Programming language              |
| Selenium WebDriver | Web automation                  |
| TestNG           | Test execution & management       |
| Log4j            | Logging framework                 |
| Maven            | Build management                  |
| Extent Report    | Reporting                         |
| Selenium Grid    | Remote execution                  |

## ✅ Steps to Run Tests
### Clone the project
````
bash
git clone https://github.com/darshgah/jpetstore_demo.git
cd jpetstore_demo
``````
### Update configuration  
  Open src/test/resources/config.properties  
  Set  
  - url = https://petstore.octoperf.com  
  - execution_env=local   # or remote for Grid


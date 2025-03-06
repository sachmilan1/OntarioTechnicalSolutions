# **Spam Detector - A Naïve Bayes Approach**

## **Overview**
In today's digital world, email has become an essential mode of communication. However, with the increasing reliance on email, the problem of **spam emails** has grown significantly. While some spam emails are easily recognizable, others are more deceptive, leading to **billions of dollars in losses for industries** each year. Spam emails not only waste time but also pose serious security threats, such as phishing attacks and malware distribution.

To tackle this issue, my partner and I, as **computer science students**, have developed a **Spam Detector**. Our spam detection system can classify emails as either **spam (unwanted emails)** or **ham (genuine emails)** using **probabilistic analysis**. By leveraging machine learning techniques, our system scans the contents of emails, calculates probabilities for each word, and determines the likelihood of an email being spam.

## **Approach & Methodology**
To build an effective spam detector, we used **machine learning techniques** with a focus on **text classification**. Our system is trained using **datasets containing spam and ham emails**, enabling it to make accurate predictions on new incoming emails.

### **1. Training Phase**
The training process involves reading a dataset of labeled emails stored in separate folders:
- **Spam Folder**: Contains spam emails
- **Ham Folder**: Contains non-spam (genuine) emails

We implemented a **Bag-of-Words model** along with **Naïve Bayes Spam Filtering** to train the classifier. Each email is broken down into individual words (unigrams), and the system counts how often each word appears in **spam** and **ham** emails.

The model calculates **word probabilities** based on:
- **P(Word | Spam)**: Probability that a word appears in spam emails.
- **P(Word | Ham)**: Probability that a word appears in ham emails.
- **P(Spam | Word)**: Probability that an email is spam given it contains a certain word.

### **2. Key Enhancements for Improved Accuracy**
To enhance the model’s performance, we implemented several **refinement techniques**:

1. **Domain Filtering for Email Addresses**  
   Spammers often use **different email addresses** but with **similar domains**. Instead of considering full email addresses, we **strip away headers** and focus only on domains to improve pattern recognition.

2. **Metadata Removal**  
   Some **non-essential metadata** (e.g., email timestamps, random IDs) can create noise in classification. We **filter out unnecessary metadata** to improve classification accuracy.

3. **Stopword Removal**  
   Common words that do not contribute to spam classification (e.g., “the,” “and,” “or”) are removed to **prevent misleading probability calculations**.

### **3. Testing Phase**
Once the spam detector is trained, we evaluate its accuracy using a **separate test dataset**.  
The test dataset consists of unseen **ham** and **spam** emails. Each test email is broken into words, and the **probability of it being spam** is calculated using the formula:

## Spam Probability Calculation

To determine whether an email is spam or ham, we use the following formulas:

![img.png](img.png)

This probability is compared against a **threshold** to determine if an email is spam or ham.

### **4. User Interface**
Our spam detector is designed with a **user-friendly and interactive interface** that allows users to:
- Select a directory containing emails for training and testing.
- Automatically classify emails based on learned probabilities.
- Display classification results in an interactive table (JTable), showing:
    - Email filename
    - Predicted classification (Spam/Ham)
    - Actual classification (Spam/Ham)
- View accuracy and precision metrics at the bottom of the interface.

## **Performance Metrics**
After testing our model on a labeled dataset, we compute two key performance indicators:

1. **Accuracy**  

2. **Precision (Spam Detection Accuracy)**

Higher accuracy and precision indicate a more effective spam filter.

## **Conclusion**
This project demonstrates the power of **Naïve Bayes classification** in detecting spam emails based on textual patterns. By applying techniques such as **domain filtering, metadata removal, and stopword elimination**, we have improved the accuracy and effectiveness of our model.

The **interactive UI and efficient probability-based classification** make our spam detector a useful tool in identifying and filtering spam emails. This project serves as a foundation for **future improvements**, such as **bigram models, deep learning approaches, and real-time email scanning**.

## **How to Run**
1. Clone the repository:
   ```
   git clone https://github.com/OntarioTech-CS-program/w25-csci2020u-assignment01-a1-gangarh-vasile.git
   ```
2. Open the project in **IntelliJ IDEA / Eclipse / VS Code**.
3. Ensure Java **(JDK 17 or higher)** is installed.
4. Run the `SpamDetector.java` file.
5. Select a directory containing **train/test datasets** when prompted.
6. View classification results and performance metrics in the GUI.

## **References**
- **Bag-of-Words Model**: [Wikipedia](https://en.wikipedia.org/wiki/Bag-of-words_model)
- **Naïve Bayes Spam Filtering**: [Wikipedia](https://en.wikipedia.org/wiki/Naive_Bayes_spam_filtering)
- **Machine Learning for Spam Detection**: Research Papers & Online Articles


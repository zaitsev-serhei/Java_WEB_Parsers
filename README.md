# JSON & HTML Parsing Toolkit

This project is a lightweight Java-based toolkit for parsing product data from two sources:

JSON files (using Jackson)

HTML pages (using Jsoup)

It includes utilities for reading and mapping JSON, scraping web pages, generating statistics over parsed products, and writing results to output files.

The project is structured in a modular way to keep parsing, configuration, utilities, and statistics separated and easy to extend.
# 🚀 Features

1. Parse product data from JSON files</br>
2. Scrape product information from web pages using Jsoup</br>
3. Convert raw data into typed Java objects (Product)</br>
4. Generate statistics (counts, aggregations, etc.)</br>
5. Write results to structured output files (JSON, CSV, text)</br>
6. Clear separation of concerns: configuration → parsing → statistics → output</br>

# 🛠️ Technology Stack
  Java 17+</br>
  Jackson Databind — JSON parsing</br>
  Jsoup — HTML parsing</br>
  Maven — build & dependency management</br>
  OOP Modular Architecture — with interfaces + implementations</br>

# 📁 Project Structure
org/vavilon_learn</br>
 ├── Main.java</br>
 ├── Product.java</br>
 ├── config</br>
 │ │  └── JsonMapperConfig.java</br>
 ├── statistics</br>
 │ │   ├── ProductStatistics.java</br>
 │ │   └── impl</br>
 │ │   │    └── ProductStatisticsImpl.java</br>
 └── utils</br>
 │ │   ├── JsonFileReader.java</br>
 │ │   ├── JsoupPageParser.java</br>
 │ │   ├── ResultWriter.java</br>
 │ │   └── impl</br>
 │ │ │       ├── JsonFileReaderImpl.java</br>
 │ │ │       ├── JsonResultWriter.java</br>
 │ │ │       └── WebParser.java</br>

# Authors
Serhii Zaitsev — https://github.com/zaitsev-serhei




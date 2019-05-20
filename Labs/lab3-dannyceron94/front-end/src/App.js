import React, { Component } from 'react';
import axios from 'axios';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  constructor(props){
    super(props);
    this.state={temp:0,message:'LALALA'}
  }
  
  loadSomeData() {
    axios.get('/hello')
      .then((res) => {
        console.log(res.data);
        this.setState({
          temp:res.data.temp,
          message: res.data.message
        });
      })
      .catch((err) => {
        console.log(err);
      });
  }

  functionName = () =>{
    console.log("hey yo B!");
    this.loadSomeData();
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          {/* <img src={logo} className="App-logo" alt="logo" />
          <p>
            Edit <code>src/App.js</code> and save to reload.
          </p>
          <a
            className="App-link"
            href="https://reactjs.org"
            target="_blank"
            rel="noopener noreferrer"
          >
            Learn React
          </a> */}
          <div>

            <div className ="widget">
            <h1>{this.state.temp}&deg;F</h1>
            <div><h3>
              BABY it is  
              {this.state.message}
              </h3></div>
            </div> 

            <button onClick = {this.functionName}>Get Data</button>
          </div>
        </header>
      </div>
    );
  }
}

export default App;

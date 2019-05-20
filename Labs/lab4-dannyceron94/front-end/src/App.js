import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      text: '',
      result: '',
    };
  }

  updateText = (e) => {
    this.setState({
      text: e.target.value,
      result: '',
    });
  };

  submitText = () => {
    this.setState(state => ({
      result: state.text,
      text: '',
    }));
  };

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <input id="input-box" value={this.state.text} onChange={this.updateText} />
          <button id="submit-button" onClick={this.submitText}>Click Me</button>
          <h1 id="result">{this.state.result}</h1>
        </header>
      </div>
    );
  }
}

export default App;

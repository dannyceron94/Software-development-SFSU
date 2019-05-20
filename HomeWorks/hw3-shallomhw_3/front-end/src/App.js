import React, { Component } from 'react';
import axios from 'axios';
import './App.css';
import { prototype } from 'module';


class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      value: '',
      list: []
    };

    this.handleChange =
    this.handleChange.bind(this);
    this.handleSubmit =
    this.handleSubmit.bind(this);

  }//end of const

  
  handleChange(event) {
    this.setState({value:event.target.value});
   
  }//end of handleChange

  handleSubmit(event) {
  //  alert('Entry was submitted: ' + this.state.value);
   this.storeToDataBase(this.state.value);
   console.log(this.state.value);
   event.preventDefault();
  }//end of handle submit

  handleSubmit2(props){
    this.deleteMethod(this.state.value);
  }

  loadInput() {
  axios.get('/hello')
  .then((res) => {
    console.log(res.data);
    this.setState({
      message: res.data.message
    })
  })
  .catch((err) => {
    console.log(err);
  });
}//end of loadInput

  fetchInput = () => {
    console.log('Saved!');
    this.loadInput();
  }//end of fetchInput

  storeToDataBase(props){
    console.log(props);
    axios.post('/store', props)
    .then((prop)=>{
      console.log(prop);
      //console.log(res.data);
    });
  }

  //________________
  DisplayList(){

    const array =[];
    let array0=[];
    axios.get('/list',Response)
    .then((Response)=>{
      console.log(Response)
      for(let i = 0; i<Response.data.res.length; i++)
      {
        var obj = {
           key: Response.data.res[i]._id,
           note: Response.data.res[i].data
        }
      
        array.push(obj);
      }
     
      console.log(array);

      this.setState({list: this.showlist(array)});
      
    })
    .catch( (error) =>{
      console.log(error);
  });
  
  //  return (
  //  <div>
  //   {array.length}
  //   {array0}
  //  </div>
  //  );
  }//end of display method

  showlist(props){
  // //const notes = props.array;
  
  let listItems = props.map((note) =>
  <div key = {note.key} className="content">

      
    <p>{note.note}</p>
    <button onClick={this.deleteMethod(note.note)}>delete {console.log(note.note)}</button>
    
  </div>
  );
  return (
    <div>{listItems}</div>
  );
}

deleteMethod(props){
  axios.delete('/delete',props)
  .then((prop)=>{
    console.log(prop)
  })
}


//______________________ end od create list
  render() {
    this.DisplayList();
    return (
      <div className="App">
        <header className="App-header">
          <div className="widget">

            <div>{this.state.message}</div>

            <form onSubmit={this.handleSubmit}> <br />
              <input rows="1" cols="45" type="text" value={this.state.value} onChange={this.handleChange}/> <br />
              <input type="submit" value="Su"/>
              <div className = "List" >
            {console.log(this.state.list)}
             {this.state.list}
            </div>
                {/* {this.storeToDataBase(this.state.value)} */}
            </form>

            {/* this makes the box ivory box that displays the text. */}
            
          </div>
      </header>
      </div>
    );
  }
}



export default App;
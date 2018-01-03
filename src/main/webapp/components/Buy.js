import React from 'react';
import { connect } from 'react-redux';
import * as pageAction from '../constants/a'

class BuyList extends React.Component{
    constructor(props){
        super(props);

    }

    render(){
        return (
            <div className="buyList">

            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        buyList: state.buyList
    }
}

export default connect(mapStateToProps)(BuyList)
import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux'
import * as buyActions from '../actions/buyActions'

class BuyList extends React.Component{
    constructor(props){
        super(props);
        const { getBuyList } = props.buyActions;
        getBuyList();
    }

    render(){
        const items = [];
        this.props.buyList.forEach((item) => {
            items.push(

            );
        });
        return (
            <div className="buyList">
                {items}
            </div>
        )
    }
}

function mapStateToProps(state) {
    console.log("mapStateToProps");
    debugger;
    return {
        buyList: state.default.buyList
    }
}

function mapDispatchToProps(dispatch){
    return{
        buyActions: bindActionCreators(buyActions, dispatch)
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(BuyList)
import React from 'react';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux'
import * as buyActions from '../actions/buyActions'
import BuyItem from './BuyItem'
import $ from 'jquery'
import * as userActions from "../actions/userActions";
import {stompClient} from "../app";

class BuyList extends React.Component {

    constructor(props) {
        super(props);
        const {getBuyList, acceptBuyList} = props.buyActions;
        getBuyList();
        if (!!$.cookie('access_token')) {
            this.props.userActions.getUserInfo($.cookie("access_token"));
        }
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/updateItems', function(buyList){
                console.log("RECIEVED");
                acceptBuyList(JSON.parse(buyList.body));
            });
        });
    }

    render() {
        const items = [];
        console.log("render buy");
        debugger;
        this.props.buyList.forEach((item) => {
            items.push(
                <BuyItem {...item}/>
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
    console.log("buy->mapStateToProps");
    debugger;
    return {
        buyList: state.getBuyList.buyList
    }
}

function mapDispatchToProps(dispatch) {
    return {
        buyActions: bindActionCreators(buyActions, dispatch),
        userActions: bindActionCreators(userActions, dispatch)
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(BuyList)
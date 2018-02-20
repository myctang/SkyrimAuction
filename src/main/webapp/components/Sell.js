import React from 'react'
import { connect } from 'react-redux';
import {withRouter} from 'react-router-dom'
import SellItem from './SellItem'
import {bindActionCreators} from "redux";
import * as sellActions from "../actions/sellActions";
import $ from 'jquery';

export class Sell extends React.Component{
    constructor(props){
        super(props);
        const { getInventory } = props.sellActions;
        getInventory($.cookie("access_token"));
    }

    render(){
        let items = [];
        if (this.props.login) {
            this.props.inventory.forEach((item) => {
                items.push(
                    <SellItem {...item}/>
                )
            });
        }else{
            items.push(
                <p>Прежде чем выставлять предметы на продажу вам необходимо зарегестрироваться</p>
            )
        }
        return(
            <div className="buyList">
                {items}
            </div>
        )
    }
}

function mapStateToProps(state) {
    console.log("mapStateToProps mainPage");
        return {
            login: state.login.login,
            inventory: state.getInventory.inventory
        }
}

function mapDispatchToProps(dispatch){
    return{
        sellActions: bindActionCreators(sellActions, dispatch)
    }
}

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Sell))
var path = require('path');

module.exports = {

    entry: './src/main/webapp/app.js',
    output: {
        path: __dirname + '/src/main/webapp/public',
        filename: 'bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.js?$/,
                loader: 'babel-loader',
                options: {
                    presets: [
                        ["es2015", {modules: false}],
                        "stage-2",
                        "react"
                    ],
                    plugins: [],
                    env: {
                        development: {
                            plugins: []
                        }
                    }
                }
            },
            {
                test: /\.css$/,
                use: [
                    "style-loader",
                    {
                        loader: "css-loader",
                        options: {
                            modules: true,
                            sourceMap: true,
                            importLoaders: 1,
                            localIdentName: "[name]--[local]--[hash:base64:8]"
                        }
                    },
                    "postcss-loader" // has separate config, see postcss.config.js nearby
                ]
            },
            {test: /\.(woff2?|ttf|eot|svg|png|jpe?g|gif)$/, loader: 'file-loader'}
        ]
    }
};
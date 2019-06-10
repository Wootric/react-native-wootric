
Pod::Spec.new do |s|
  s.name         = "RNWootric"
  s.version      = package['version']
  s.summary      = "React Native package for Wootric SDK"
  s.homepage     = "https://github.com/Wootric/react-native-wootric"
  s.license      = { :type => "MIT", :file => "LICENSE" }
  s.author       = { "author" => "author@domain.cn" }
  s.platform     = :ios, "8.0"
  s.source       = { :git => "https://github.com/Wootric/react-native-wootric.git" }
  s.source_files = "ios/**/*.{h,m}"
  s.requires_arc = true

  s.dependency "React"
  s.dependency "WootricSDK"

end


